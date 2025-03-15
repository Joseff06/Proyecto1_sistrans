-- Archivo: consultas_rfc.sql
-- Este archivo contiene las consultas correspondientes a los siguientes RFC:
-- RFC1: Consultar la agenda de disponibilidad para un servicio de salud en las próximas 4 semanas.
-- RFC2: Mostrar los 20 servicios más solicitados en un período dado.
-- RFC3: Mostrar el índice de uso de cada uno de los servicios provistos en un período dado.
-- RFC4: Mostrar la utilización de servicios de EPSANDES por un afiliado en un período dado.

---------------------------------------------------------------
-- RFC1 - CONSULTAR LA AGENDA DE DISPONIBILIDAD QUE TIENE UN SERVICIO DE SALUD
-- INGRESADO POR EL USUARIO EN LAS SIGUIENTES 4 SEMANAS.
-- Se recibe el código del servicio. Para cada horario se presenta:
-- nombre del servicio, fecha y hora de disponibilidad, la IPS que lo ofrece,
-- y el nombre del médico que lo prestaría.
---------------------------------------------------------------
SELECT ss.nombre AS nombreServicio, d.fecha, d.estadoDisponibilidad, i.nombreIPS, m.nombreMedico 
FROM DISPONIBILIDAD d 
JOIN SERVICIOS_SALUD ss ON d.codigoServicio = ss.codigo 
JOIN IPS_SERVICIOS_SALUD iss ON ss.codigo = iss.codigoServicio 
JOIN IPS i ON iss.nitIPS = i.nitIPS 
JOIN MEDICO m ON d.regMedico = m.regMedico 
WHERE d.codigoServicio = :codigoServicio 
  AND d.fecha BETWEEN SYSDATE AND SYSDATE + INTERVAL '4' WEEK;

---------------------------------------------------------------
-- RFC2 - MOSTRAR LOS 20 SERVICIOS MÁS SOLICITADOS.
-- Se consideran los servicios más solicitados en un período de tiempo dado.
---------------------------------------------------------------
SELECT ss.nombre, COUNT(os.idOrden) AS totalSolicitudes 
FROM ORDEN_SERVICIO os 
JOIN SERVICIOS_SALUD ss ON os.codigoServicio = ss.codigo 
WHERE os.fechaOrden BETWEEN :fechaInicio AND :fechaFin 
GROUP BY ss.nombre 
ORDER BY totalSolicitudes DESC 
FETCH FIRST 20 ROWS ONLY;

---------------------------------------------------------------
-- RFC3 - MOSTRAR EL ÍNDICE DE USO DE CADA UNO DE LOS SERVICIOS PROVISTOS.
-- El índice se calcula dividiendo el número de servicios disponibles por el número de servicios usados
-- en el período (fecha inicio – fecha finalización) ingresado por el usuario.
---------------------------------------------------------------
SELECT ss.nombre, 
       (SELECT COUNT(*) FROM DISPONIBILIDAD d 
         WHERE d.codigoServicio = ss.codigo AND d.fecha BETWEEN :fechaInicio AND :fechaFin) AS serviciosDisponibles, 
       (SELECT COUNT(*) FROM ORDEN_SERVICIO os 
         WHERE os.codigoServicio = ss.codigo AND os.fechaOrden BETWEEN :fechaInicio AND :fechaFin) AS serviciosUsados, 
       (SELECT COUNT(*) FROM ORDEN_SERVICIO os 
         WHERE os.codigoServicio = ss.codigo AND os.fechaOrden BETWEEN :fechaInicio AND :fechaFin) / 
       (SELECT COUNT(*) FROM DISPONIBILIDAD d 
         WHERE d.codigoServicio = ss.codigo AND d.fecha BETWEEN :fechaInicio AND :fechaFin) AS indiceUso 
FROM SERVICIOS_SALUD ss;

---------------------------------------------------------------
-- RFC4 - MOSTRAR LA UTILIZACIÓN DE SERVICIOS DE EPSANDES POR UN AFILIADO DADO,
-- EN UN PERÍODO DADO (RANGO DE FECHAS INDICADO).
-- La consulta recibe el identificador del paciente, fecha de inicio y fecha de terminación.
-- Cada fila presenta: nombre del servicio, fecha en que fue tomado, médico que lo atendió y la IPS que lo ofreció.
---------------------------------------------------------------
SELECT ss.nombre AS nombreServicio, os.fechaOrden, m.nombreMedico, i.nombreIPS 
FROM ORDEN_SERVICIO os 
JOIN SERVICIOS_SALUD ss ON os.codigoServicio = ss.codigo 
JOIN MEDICO m ON os.regMedico = m.regMedico 
JOIN MEDICO_IPS mi ON m.regMedico = mi.regMedico 
JOIN IPS i ON mi.nitIPS = i.nitIPS 
WHERE os.numDocAfiliado = :numDocAfiliado 
  AND os.fechaOrden BETWEEN :fechaInicio AND :fechaFin;
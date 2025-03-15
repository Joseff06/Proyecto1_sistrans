-- ************************************************************
-- Script de Creación de Tablas para el esquema EPSANDES
-- ************************************************************

-- 1. Tabla EPS: Entidades Promotoras de Salud (aseguradoras).
--    Almacena las EPS disponibles. PK = numEps.
CREATE TABLE EPS (
    numEps       NUMBER(5)    CONSTRAINT pk_eps PRIMARY KEY,   -- Código único de la EPS (PK)
    nombreEps    VARCHAR2(100)    NOT NULL                     -- Nombre de la EPS
    -- Podríamos agregar más campos (dirección, etc.) si fuera necesario
);
-- Restricción UNIQUE opcional: nombreEps único para evitar duplicados de nombre
ALTER TABLE EPS ADD CONSTRAINT uq_eps_nombre UNIQUE(nombreEps);

-- 2. Tabla SERVICIOS_SALUD: Catálogo de servicios de salud ofrecidos.
--    Almacena tipos de servicios (procedimientos, consultas, etc.). PK = codigo.
CREATE TABLE SERVICIOS_SALUD (
    codigo       NUMBER(5)    CONSTRAINT pk_servicio PRIMARY KEY,  -- Código del servicio de salud (PK)
    nombre       VARCHAR2(100)    NOT NULL,                        -- Nombre del servicio
    descripcion  VARCHAR2(255)    NOT NULL                         -- Descripción del servicio
);
-- Evitar nombres de servicio duplicados
ALTER TABLE SERVICIOS_SALUD ADD CONSTRAINT uq_servicio_nombre UNIQUE(nombre);

-- 3. Tabla AFILIADO: Personas afiliadas al sistema (usuarios del servicio de salud).
--    Almacena datos personales y tipo de afiliación. PK = numDoc.
CREATE TABLE AFILIADO (
    numDoc           NUMBER(10)   CONSTRAINT pk_afiliado PRIMARY KEY,  -- Número de documento del afiliado (PK)
    nombreAfiliado   VARCHAR2(100)    NOT NULL,                        -- Nombre del afiliado
    tipoAfiliacion   CHAR(1)         NOT NULL,                        -- Tipo de afiliación: 'C' (Contribuyente) o 'B' (Beneficiario)
    fkContribuyente  NUMBER(10)         NULL                           -- Si es beneficiario (B), documento de su afiliado contribuyente
);
-- Clave foránea recursiva: fkContribuyente referencia a otro Afiliado (relación Contribuyente-Beneficiario).
ALTER TABLE AFILIADO 
  ADD CONSTRAINT fk_afiliado_contrib FOREIGN KEY (fkContribuyente) 
      REFERENCES AFILIADO(numDoc);

-- Restricción CHECK: garantizar valores válidos de tipoAfiliacion y coherencia con fkContribuyente.
ALTER TABLE AFILIADO 
  ADD CONSTRAINT chk_afiliado_tipo CHECK (
      (tipoAfiliacion IN ('C','B')) 
      AND ( -- Si es Contribuyente, no debe tener fkContribuyente; si es Beneficiario, debe tenerlo.
           (tipoAfiliacion = 'C' AND fkContribuyente IS NULL) 
        OR (tipoAfiliacion = 'B' AND fkContribuyente IS NOT NULL)
      )
  );

-- 4. Tabla MEDICO: Médicos que atienden a los afiliados.
--    Almacena datos del médico. PK = regMedico (registro único del médico).
CREATE TABLE MEDICO (
    regMedico    NUMBER(10)   CONSTRAINT pk_medico PRIMARY KEY,   -- Número de registro del médico (PK)
    nombreMedico VARCHAR2(100)    NOT NULL,                       -- Nombre del médico
    numDocumento NUMBER(10)       NOT NULL                        -- Cédula o documento de identidad del médico
    -- Se podrían agregar especialidad, etc.
);
-- Asegurar que no existan dos médicos con el mismo documento de identidad
ALTER TABLE MEDICO ADD CONSTRAINT uq_medico_numdoc UNIQUE(numDocumento);

-- 5. Tabla IPS: Instituciones Prestadoras de Salud (hospitales, clínicas).
--    Almacena las IPS y la EPS a la que pertenece cada una. PK = idIps. FK hacia EPS.
CREATE TABLE IPS (
    nitIPS         VARCHAR2(50)   CONSTRAINT pk_ips PRIMARY KEY,   -- Identificador de la IPS (PK)
    nombreIPS     VARCHAR2(100)   NOT NULL,                    -- Nombre de la IPS
    direccion     VARCHAR2(150)   NOT NULL,                    -- Dirección de la IPS
    telefono      VARCHAR2(20)    NOT NULL,                    -- Teléfono de contacto
    idEps         NUMBER(5)       NOT NULL                     -- EPS a la que está asociada la IPS (FK a EPS)
);
-- Clave foránea: la IPS debe referenciar una EPS existente.
ALTER TABLE IPS 
  ADD CONSTRAINT fk_ips_eps FOREIGN KEY (idEps) 
      REFERENCES EPS(numEps);
-- 6. Tabla DISPONIBILIDAD: Disponibilidad de servicios por parte de un médico en cierta fecha.
--    Registra que un médico ofrece un determinado servicio en una fecha dada y su estado (disponible/ocupado).
--    PK = idDisponibilidad.
CREATE TABLE DISPONIBILIDAD (
    idDisponibilidad    NUMBER(10)  CONSTRAINT pk_disponibilidad PRIMARY KEY,  -- Identificador único de la disponibilidad (PK)
    fecha               DATE            NOT NULL,                             -- Fecha de la disponibilidad (día específico)
    codigoServicio      NUMBER(5)       NOT NULL,                             -- Servicio disponible (FK a SERVICIOS_SALUD.codigo)
    estadoDisponibilidad VARCHAR2(20)    NOT NULL,                             -- Estado de la disponibilidad (ej: 'DISPONIBLE' u 'OCUPADO')
    regMedico           NUMBER(10)      NOT NULL                              -- Médico que ofrece la disponibilidad (FK a MEDICO.regMedico)
);
-- Claves foráneas: referencia al servicio de salud y al médico correspondientes.
ALTER TABLE DISPONIBILIDAD 
  ADD CONSTRAINT fk_disp_serv FOREIGN KEY (codigoServicio) 
      REFERENCES SERVICIOS_SALUD(codigo);
ALTER TABLE DISPONIBILIDAD 
  ADD CONSTRAINT fk_disp_med FOREIGN KEY (regMedico) 
      REFERENCES MEDICO(regMedico);
-- Restricción CHECK: limitar los valores permitidos para estadoDisponibilidad.
ALTER TABLE DISPONIBILIDAD 
  ADD CONSTRAINT chk_disp_estado CHECK (estadoDisponibilidad IN ('DISPONIBLE','OCUPADO'));
-- Restricción UNIQUE: un mismo médico no puede tener duplicada disponibilidad para el mismo servicio en la misma fecha.
ALTER TABLE DISPONIBILIDAD 
  ADD CONSTRAINT uq_disp_med_fecha UNIQUE(regMedico, fecha, codigoServicio);

-- 7. Tabla CITA: Citas médicas agendadas entre un afiliado y un médico.
--    Registra una cita (consulta) con fecha y hora específicas. PK = idCita. FK hacia Afiliado y Medico.
CREATE TABLE CITA (
    idCita          NUMBER(10)  CONSTRAINT pk_cita PRIMARY KEY,   -- Identificador único de la cita (PK)
    fechaCita       DATE            NOT NULL,                     -- Fecha de la cita
    horaCita        NUMBER(2)       NOT NULL,                     -- Hora de la cita (0-23, formato 24h)
    numDocAfiliado  NUMBER(10)      NOT NULL,                     -- Afiliado que tiene la cita (FK a AFILIADO.numDoc)
    regMedico       NUMBER(10)      NOT NULL                      -- Médico que atiende la cita (FK a MEDICO.regMedico)
);
-- Claves foráneas: afiliado y médico deben existir.
ALTER TABLE CITA 
  ADD CONSTRAINT fk_cita_afiliado FOREIGN KEY (numDocAfiliado) 
      REFERENCES AFILIADO(numDoc);
ALTER TABLE CITA 
  ADD CONSTRAINT fk_cita_med FOREIGN KEY (regMedico) 
      REFERENCES MEDICO(regMedico);
-- Restricción CHECK: la hora de la cita debe estar entre 0 y 23 (horas válidas).
ALTER TABLE CITA 
  ADD CONSTRAINT chk_cita_hora CHECK (horaCita >= 0 AND horaCita < 24);
-- Restricción UNIQUE: evitar doble agendamiento del mismo médico en la misma fecha y hora.
ALTER TABLE CITA 
  ADD CONSTRAINT uq_cita_med_fecha UNIQUE(regMedico, fechaCita, horaCita);

-- 8. Tabla ORDEN_SERVICIO: Órdenes de servicio generadas (ej.: solicitud de exámenes o procedimientos para un afiliado).
--    Registra que un médico ordena un servicio de salud para un afiliado en una fecha dada. PK = idOrden. FK hacia Afiliado, Medico y Servicio.
CREATE TABLE ORDEN_SERVICIO (
    idOrden         NUMBER(10)  CONSTRAINT pk_orden PRIMARY KEY,  -- Identificador de la orden de servicio (PK)
    fechaOrden      DATE            NOT NULL,                     -- Fecha de emisión de la orden
    numDocAfiliado  NUMBER(10)      NOT NULL,                     -- Afiliado para quien es la orden (FK a AFILIADO.numDoc)
    regMedico       NUMBER(10)      NOT NULL,                     -- Médico que genera la orden (FK a MEDICO.regMedico)
    codigoServicio  NUMBER(5)       NOT NULL                      -- Servicio solicitado (FK a SERVICIOS_SALUD.codigo)
);
-- Claves foráneas: validar existencia de afiliado, médico y servicio referenciados.
ALTER TABLE ORDEN_SERVICIO 
  ADD CONSTRAINT fk_orden_afiliado FOREIGN KEY (numDocAfiliado) 
      REFERENCES AFILIADO(numDoc);
ALTER TABLE ORDEN_SERVICIO 
  ADD CONSTRAINT fk_orden_med FOREIGN KEY (regMedico) 
      REFERENCES MEDICO(regMedico);
ALTER TABLE ORDEN_SERVICIO 
  ADD CONSTRAINT fk_orden_serv FOREIGN KEY (codigoServicio) 
      REFERENCES SERVICIOS_SALUD(codigo);

-- 9. Tabla MEDICO_IPS: Tabla puente entre Médicos e IPS.
--    Indica en qué IPS trabaja cada médico. PK compuesta = (regMedico, idIps).
CREATE TABLE MEDICO_IPS (
    regMedico   NUMBER(10)   NOT NULL,  -- Médico (FK a MEDICO.regMedico)
    idIps       NUMBER(5)    NOT NULL,  -- IPS (FK a IPS.idIps)
    -- Definición de PK compuesta
    CONSTRAINT pk_medico_ips PRIMARY KEY(regMedico, idIps)
);
-- Claves foráneas para la tabla puente:
ALTER TABLE MEDICO_IPS 
  ADD CONSTRAINT fk_medicoips_med FOREIGN KEY (regMedico) 
      REFERENCES MEDICO(regMedico);
ALTER TABLE MEDICO_IPS 
  ADD CONSTRAINT fk_medicoips_ips FOREIGN KEY (idIps) 
      REFERENCES IPS(idIps);

-- 10. Tabla IPS_SERVICIOS_SALUD: Tabla puente entre IPS y Servicios de Salud.
--     Indica qué servicios ofrece cada IPS. PK compuesta = (idIps, codigoServicio).
CREATE TABLE IPS_SERVICIOS_SALUD (
    idIps          NUMBER(5)   NOT NULL,  -- IPS (FK a IPS.idIps)
    codigoServicio NUMBER(5)   NOT NULL,  -- Servicio (FK a SERVICIOS_SALUD.codigo)
    -- Definición de PK compuesta
    CONSTRAINT pk_ips_serv PRIMARY KEY(idIps, codigoServicio)
);
-- Claves foráneas de la tabla puente:
ALTER TABLE IPS_SERVICIOS_SALUD 
  ADD CONSTRAINT fk_ipsserv_ips FOREIGN KEY (idIps) 
      REFERENCES IPS(idIps);
ALTER TABLE IPS_SERVICIOS_SALUD 
  ADD CONSTRAINT fk_ipsserv_serv FOREIGN KEY (codigoServicio) 
      REFERENCES SERVICIOS_SALUD(codigo);

-- ************************************************************
-- Fin del script de creación de tablas
-- ************************************************************
-- ************************************************************
-- Script de Creación de Tablas para el esquema EPSANDES
-- ************************************************************

-- 1. Tabla EPS: Entidades Promotoras de Salud (aseguradoras).
--    Almacena las EPS disponibles. PK = numEps.

CREATE TABLE EPS (
    numEps    NUMBER(5) CONSTRAINT pk_eps PRIMARY KEY,   -- Código único de la EPS (PK)
    nombreEps VARCHAR2(100) NOT NULL                      -- Nombre de la EPS
);
ALTER TABLE EPS ADD CONSTRAINT uq_eps_nombre UNIQUE(nombreEps);
COMMIT;

-- 2. Tabla IPS: Instituciones Prestadoras de Salud (hospitales, clínicas).
--    Usamos "nitIPS" como clave primaria y "idEps" para la FK hacia EPS.
CREATE TABLE IPS (
    nitIPS    VARCHAR2(50) PRIMARY KEY,          -- Identificador de la IPS (PK)
    nombreIPS VARCHAR2(100) NOT NULL,              -- Nombre de la IPS
    direccion VARCHAR2(150) NOT NULL,              -- Dirección de la IPS
    telefono  VARCHAR2(20) NOT NULL,               -- Teléfono de contacto
    idEps     NUMBER(5) NOT NULL                   -- EPS asociada (FK a EPS)
);
COMMIT;

-- Ahora, añade la clave foránea a la tabla IPS referenciando EPS.
ALTER TABLE IPS
  ADD CONSTRAINT fk_ips_eps FOREIGN KEY (idEps)
      REFERENCES EPS(numEps);
COMMIT;

-- 3. Tabla SERVICIOS_SALUD: Catálogo de servicios de salud ofrecidos.
CREATE TABLE SERVICIOS_SALUD (
    codigo      NUMBER(5) CONSTRAINT pk_servicio PRIMARY KEY,  -- Código del servicio (PK)
    nombre      VARCHAR2(100) NOT NULL,                        -- Nombre del servicio
    descripcion VARCHAR2(255) NOT NULL                         -- Descripción del servicio
);
ALTER TABLE SERVICIOS_SALUD ADD CONSTRAINT uq_servicio_nombre UNIQUE(nombre);
COMMIT;

-- 4. Tabla AFILIADO: Personas afiliadas al sistema.
CREATE TABLE AFILIADO (
    numDoc          NUMBER(10) CONSTRAINT pk_afiliado PRIMARY KEY,  -- Número de documento (PK)
    nombreAfiliado  VARCHAR2(100) NOT NULL,                        -- Nombre del afiliado
    tipoAfiliacion  CHAR(1) NOT NULL,                              -- 'C' o 'B'
    fkContribuyente NUMBER(10) NULL                               -- Si es beneficiario, documento del contribuyente
);
ALTER TABLE AFILIADO 
  ADD CONSTRAINT fk_afiliado_contrib FOREIGN KEY (fkContribuyente) 
      REFERENCES AFILIADO(numDoc);
ALTER TABLE AFILIADO 
  ADD CONSTRAINT chk_afiliado_tipo CHECK (
      (tipoAfiliacion IN ('C','B')) AND 
      ((tipoAfiliacion = 'C' AND fkContribuyente IS NULL) OR 
       (tipoAfiliacion = 'B' AND fkContribuyente IS NOT NULL))
  );
COMMIT;

-- 5. Tabla MEDICO: Médicos del sistema.
CREATE TABLE MEDICO (
    regMedico    NUMBER(10) CONSTRAINT pk_medico PRIMARY KEY,  -- Registro del médico (PK)
    nombreMedico VARCHAR2(100) NOT NULL,                     -- Nombre del médico
    numDocumento NUMBER(10) NOT NULL                          -- Documento de identidad
);
ALTER TABLE MEDICO ADD CONSTRAINT uq_medico_numdoc UNIQUE(numDocumento);
COMMIT;

-- 6. Tabla DISPONIBILIDAD: Disponibilidad de un médico para un servicio.
CREATE TABLE DISPONIBILIDAD (
    idDisponibilidad    NUMBER(10) CONSTRAINT pk_disponibilidad PRIMARY KEY,  -- PK
    fecha               DATE NOT NULL,                                              -- Fecha
    codigoServicio      NUMBER(5) NOT NULL,                                           -- FK a SERVICIOS_SALUD
    estadoDisponibilidad VARCHAR2(20) NOT NULL,                                        -- 'DISPONIBLE' u 'OCUPADO'
    regMedico           NUMBER(10) NOT NULL                                             -- FK a MEDICO
);
ALTER TABLE DISPONIBILIDAD 
  ADD CONSTRAINT fk_disp_serv FOREIGN KEY (codigoServicio) 
      REFERENCES SERVICIOS_SALUD(codigo);
ALTER TABLE DISPONIBILIDAD 
  ADD CONSTRAINT fk_disp_med FOREIGN KEY (regMedico) 
      REFERENCES MEDICO(regMedico);
ALTER TABLE DISPONIBILIDAD 
  ADD CONSTRAINT chk_disp_estado CHECK (estadoDisponibilidad IN ('DISPONIBLE','OCUPADO'));
ALTER TABLE DISPONIBILIDAD 
  ADD CONSTRAINT uq_disp_med_fecha UNIQUE(regMedico, fecha, codigoServicio);
COMMIT;

-- 7. Tabla CITA: Citas médicas.
CREATE TABLE CITA (
    idCita         NUMBER(10) CONSTRAINT pk_cita PRIMARY KEY,  -- PK
    fechaCita      DATE NOT NULL,                                  -- Fecha de la cita
    horaCita       NUMBER(2) NOT NULL,                             -- Hora (0-23)
    numDocAfiliado NUMBER(10) NOT NULL,                             -- FK a AFILIADO
    regMedico      NUMBER(10) NOT NULL                              -- FK a MEDICO
);
ALTER TABLE CITA 
  ADD CONSTRAINT fk_cita_afiliado FOREIGN KEY (numDocAfiliado) 
      REFERENCES AFILIADO(numDoc);
ALTER TABLE CITA 
  ADD CONSTRAINT fk_cita_med FOREIGN KEY (regMedico) 
      REFERENCES MEDICO(regMedico);
ALTER TABLE CITA 
  ADD CONSTRAINT chk_cita_hora CHECK (horaCita >= 0 AND horaCita < 24);
ALTER TABLE CITA 
  ADD CONSTRAINT uq_cita_med_fecha UNIQUE(regMedico, fechaCita, horaCita);
COMMIT;

-- 8. Tabla ORDEN_SERVICIO: Órdenes de servicio.
CREATE TABLE ORDEN_SERVICIO (
    idOrden         NUMBER(10) CONSTRAINT pk_orden PRIMARY KEY,  -- PK
    fechaOrden      DATE NOT NULL,                                  -- Fecha de orden
    numDocAfiliado  NUMBER(10) NOT NULL,                              -- FK a AFILIADO
    regMedico       NUMBER(10) NOT NULL,                              -- FK a MEDICO
    codigoServicio  NUMBER(5) NOT NULL                                -- FK a SERVICIOS_SALUD
);
ALTER TABLE ORDEN_SERVICIO 
  ADD CONSTRAINT fk_orden_afiliado FOREIGN KEY (numDocAfiliado) 
      REFERENCES AFILIADO(numDoc);
ALTER TABLE ORDEN_SERVICIO 
  ADD CONSTRAINT fk_orden_med FOREIGN KEY (regMedico) 
      REFERENCES MEDICO(regMedico);
ALTER TABLE ORDEN_SERVICIO 
  ADD CONSTRAINT fk_orden_serv FOREIGN KEY (codigoServicio) 
      REFERENCES SERVICIOS_SALUD(codigo);
COMMIT;

-- 9. Tabla MEDICO_IPS: Relación entre Médicos e IPS.
CREATE TABLE MEDICO_IPS (
    regMedico NUMBER(10) NOT NULL,           -- FK a MEDICO
    nitIPS    VARCHAR2(50) NOT NULL,          -- FK a IPS
    CONSTRAINT pk_medico_ips PRIMARY KEY (regMedico, nitIPS)
);
ALTER TABLE MEDICO_IPS 
  ADD CONSTRAINT fk_medicoips_med FOREIGN KEY (regMedico) 
      REFERENCES MEDICO(regMedico);
ALTER TABLE MEDICO_IPS 
  ADD CONSTRAINT fk_medicoips_ips FOREIGN KEY (nitIPS) 
      REFERENCES IPS(nitIPS);
COMMIT;

-- 10. Tabla IPS_SERVICIOS_SALUD: Relación entre IPS y Servicios de Salud.
CREATE TABLE IPS_SERVICIOS_SALUD (
    nitIPS         VARCHAR2(50) NOT NULL,  -- FK a IPS
    codigoServicio NUMBER(5) NOT NULL,       -- FK a SERVICIOS_SALUD
    CONSTRAINT pk_ips_serv PRIMARY KEY (nitIPS, codigoServicio)
);
ALTER TABLE IPS_SERVICIOS_SALUD 
  ADD CONSTRAINT fk_ipsserv_ips FOREIGN KEY (nitIPS) 
      REFERENCES IPS(nitIPS);
ALTER TABLE IPS_SERVICIOS_SALUD 
  ADD CONSTRAINT fk_ipsserv_serv FOREIGN KEY (codigoServicio) 
      REFERENCES SERVICIOS_SALUD(codigo);
COMMIT;

-- ************************************************************
-- Fin del script de creación de tablas
-- ************************************************************
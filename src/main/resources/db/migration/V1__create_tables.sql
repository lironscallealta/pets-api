-- 1. Tabla Maestra 
CREATE TABLE especies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_especie VARCHAR(30) NOT NULL,
    descripcion_especie VARCHAR(200),
    exotica_boolean BOOLEAN NOT NULL
);

-- 2. Tabla Intermedia
CREATE TABLE razas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_raza VARCHAR(30) DEFAULT 'Mestizo',
    descripcion_raza VARCHAR(200),
    id_especie BIGINT NOT NULL, 
    CONSTRAINT fk_raza_especie FOREIGN KEY (id_especie) REFERENCES especies(id)
);

-- 3. Tabla Final
CREATE TABLE mascotas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_mascota VARCHAR(50) NOT NULL,
    fecha_nacimiento_mascota DATE,
    es_docil_boolean BOOLEAN DEFAULT TRUE,
    id_raza BIGINT NOT NULL, 
    id_cliente BIGINT, 
    CONSTRAINT fk_mascota_raza FOREIGN KEY (id_raza) REFERENCES razas(id)
);
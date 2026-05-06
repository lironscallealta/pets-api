-- 1. Especie: Perro (Tomará el ID 2 automáticamente tras el V2 de Gatos)
INSERT INTO especies (nombre_especie, descripcion_especie, exotica_boolean) 
VALUES ('Perro', 'Canis lupus familiaris. Mamífero carnívoro doméstico de la familia Canidae.', false);

-- 2. Catálogo de 10 Razas para Perros (Asociadas al ID 2 de especie)
INSERT INTO razas (nombre_raza, descripcion_raza, id_especie) 
VALUES 
('Pastor Alemán', 'Alta inteligencia y obediencia. Aptitud para trabajo y guardia.', 2),
('Golden Retriever', 'Morfología equilibrada. Cobrador de alta docilidad.', 2),
('Bulldog Inglés', 'Braquicéfalo de estructura compacta y musculatura pesada.', 2),
('Poodle', 'Manto rizado de crecimiento continuo. Alta capacidad de aprendizaje.', 2),
('Labrador Retriever', 'Complexión fuerte y resistente. Destaca por su aptitud acuática.', 2),
('Chihuahua', 'Raza miniatura de origen mexicano. Cráneo abovedado.', 2),
('Husky Siberiano', 'Morfología tipo spitz. Gran resistencia física en climas fríos.', 2),
('Dóberman', 'Complexión atlética y esbelta. Instinto protector marcado.', 2),
('Border Collie', 'Raza de pastoreo. Sobresale por su agilidad y concentración.', 2),
('Mestizo Canino', 'Sin linaje definido. Alta variabilidad y resistencia biológica.', 2);
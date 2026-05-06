-- 1. Especie: Gato (ID autogenerado: 1)
INSERT INTO especies (nombre_especie, descripcion_especie, exotica_boolean) 
VALUES ('Gato', 'Felis catus. Felino doméstico ágil de tamaño pequeño.', false);

-- 2. Catalogo de 10 Razas para Gatos (Asociadas al ID 1 de especie)
INSERT INTO razas (nombre_raza, descripcion_raza, id_especie) 
VALUES 
('Siamés', 'Cuerpo esbelto, patrón colorpoint y origen tailandés.', 1),
('Persa', 'Braquicéfalo de pelaje largo. Origen iraní.', 1),
('Maine Coon', 'Raza gigante de EE.UU. Pelaje impermeable.', 1),
('Azul Ruso', 'Pelaje corto denso y color gris plateado.', 1),
('Bengala', 'Híbrido con patrón de manchas tipo roseta.', 1),
('Esfinge', 'Ausencia aparente de pelaje por mutación natural.', 1),
('Ragdoll', 'Tamaño grande, ojos azules y alta docilidad.', 1),
('Británico de Pelo Corto', 'Cuerpo compacto y pelaje muy denso.', 1),
('Bosque de Noruega', 'Raza nórdica de gran tamaño y pelaje denso.', 1),
('Mestizo Felino', 'Sin linaje definido. Alta resistencia biológica.', 1);

--v1
--cree solo 10 para hacerlo escalable poco a poco

-- 1. Especies Exóticas (IDs autogenerados: 3, 4 y 5)
-- Creamos primero las especies para que existan los IDs antes de las razas
INSERT INTO especies (nombre_especie, descripcion_especie, exotica_boolean) 
VALUES 
('Reptil', 'Animal vertebrado de sangre fría con piel cubierta de escamas.', true), -- ID 3
('Tortuga', 'Reptil caracterizado por tener un caparazón duro que protege sus órganos.', true), -- ID 4
('Ave', 'Animal vertebrado con plumas, alas y pico.', true); -- ID 5

-- 2. Catálogo de 10 Variedades Exóticas
-- IMPORTANTE: Usamos id_especie (singular) para coincidir con tu V1
INSERT INTO razas (nombre_raza, descripcion_raza, id_especie) 
VALUES 
-- Reptiles (Asociadas al ID 3)
('Iguana Verde', 'Lagarto trepador de color verde brillante con una cresta en la espalda.', 3),
('Gecko Leopardo', 'Reptil nocturno de tamaño pequeño, con manchas y de fácil cuidado.', 3),
('Dragón Barbudo', 'Lagarto dócil que infla una "barba" de escamas oscuras bajo el cuello.', 3),

-- Tortugas (Asociadas al ID 4)
('Tortuga de Orejas Rojas', 'Tortuga de agua dulce muy común, con manchas rojas a los lados de la cabeza.', 4),
('Tortuga Rusa', 'Tortuga de tierra pequeña, muy resistente y de caparazón redondeado.', 4),
('Tortuga de Tierra Chilena', 'Especie terrestre herbívora nativa de Chile, protegida por la ley.', 4),

-- Aves (Asociadas al ID 5)
('Ninfa', 'Pájaro amigable con una cresta en la cabeza y características manchas naranjas.', 5),
('Agapornis', 'Pájaro pequeño muy sociable, conocido popularmente como "inseparable".', 5),
('Cacatúa', 'Ave muy inteligente de plumaje blanco y una cresta de plumas móvil.', 5),
('Guacamayo Azul', 'Loro de gran tamaño, pico muy fuerte y plumaje color azul intenso.', 5);
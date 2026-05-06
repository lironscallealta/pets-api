-- 1. Nuevas Especies Domésticas (IDs autogenerados: 6, 7 y 8)
INSERT INTO especies (nombre_especie, descripcion_especie, exotica_boolean) 
VALUES 
('Conejo', 'Oryctolagus cuniculus. Mamífero lagomorfo herbívoro de orejas largas.', false),
('Caballo', 'Equus ferus caballus. Mamífero ungulado de gran porte y resistencia.', false),
('Roedor', 'Pequeños mamíferos del orden Rodentia de convivencia doméstica.', false);

-- 2. Catálogo de 10 Variedades (Asociadas a IDs 6, 7 y 8)
INSERT INTO razas (nombre_raza, descripcion_raza, id_especie) 
VALUES 
-- Conejos (ID 6)
('Holandés Enano', 'Raza de tamaño muy compacto, orejas cortas y erectas.', 6),
('Belier', 'Caracterizado por orejas largas y caídas a los lados de la cabeza.', 6),
('Angora', 'Variedad seleccionada por su pelaje extremadamente largo y sedoso.', 6),

-- Caballos (ID 7)
('Caballo Chileno', 'Raza corralera de gran resistencia, musculatura densa y baja alzada.', 7),
('Pura Sangre Inglés', 'Caballo de gran agilidad y velocidad, contextura atlética.', 7),
('Árabe', 'Raza antigua de gran resistencia y cabeza de perfil cóncavo.', 7),

-- Roedores (ID 8)
('Hámster Sirio', 'Roedor de mejillas expandibles (abazones) y hábitos solitarios.', 8),
('Cobaya', 'También conocido como cuy. Roedor herbívoro de cuerpo robusto.', 8),
('Chinchilla', 'Originaria de los Andes. Pelaje denso y suave, adaptada al frío.', 8),
('Jerbo', 'Pequeño roedor de extremidades posteriores largas aptas para el salto.', 8);
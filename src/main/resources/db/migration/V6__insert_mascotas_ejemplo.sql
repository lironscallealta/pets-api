-- Insertar 3 mascotas de ejemplo (para tener datos de prueba en la tabla)
INSERT INTO mascotas (nombre_mascota, fecha_nacimiento_mascota, es_docil_boolean, id_raza, id_cliente) 
VALUES 
('Mishu', '2022-04-15', TRUE, 1, 1),   -- id_raza 1 corresponde a Gato Siamés
('Rex', '2020-08-10', TRUE, 11, 2),  -- id_raza 11 corresponde a Perro Pastor Alemán
('Toby', '2023-01-20', FALSE, 20, 3); -- id_raza 20 corresponde a Perro Mestizo

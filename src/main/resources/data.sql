INSERT INTO SPACESHIP (id, name, model, manufacturer) VALUES
('c224faa8-58bc-4ab5-a439-5220bba8f954', 'Nave 1', 'Modelo 1', 'Fabricante 1'),
(UUID(), 'Nave 2', 'Modelo 2', 'Fabricante 2'),
(UUID(), 'Nave 3', 'Modelo 3', 'Fabricante 3'),
(UUID(), 'Nave 4', 'Modelo 4', 'Fabricante 4');

INSERT INTO REPAIR (id, spaceship_id, fault, finished) VALUES
('2b151fd6-86e5-4529-a04e-4e9920008118', 'c224faa8-58bc-4ab5-a439-5220bba8f954', 'many many many...', false);

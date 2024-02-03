INSERT INTO SPACESHIP (id, name, model, manufacturer) VALUES
('c224faa8-58bc-4ab5-a439-5220bba8f954', 'Nave 1', 'Modelo 1', 'Fabricante 1'),
(UUID(), 'Nave 2', 'Modelo 2', 'Fabricante 2'),
(UUID(), 'Nave 3', 'Modelo 3', 'Fabricante 3'),
(UUID(), 'Nave 4', 'Modelo 4', 'Fabricante 4');

INSERT INTO REPAIR (id, spaceship_id, fault, finished) VALUES
('2b151fd6-86e5-4529-a04e-4e9920008118', 'c224faa8-58bc-4ab5-a439-5220bba8f954', 'many many many...', false);

INSERT INTO CREW_MEMBER (id, name) VALUES
('8c71492b-2a26-4e11-bd0f-365b57455b8f	', 'Tripulante 1'),
('78b53102-eed9-44c0-b290-8f8a8328f064	', 'Tripulante 2');

INSERT INTO JOURNEY (id, spaceship_id, goal, date) VALUES
('eccebb2d-1a46-44b2-b311-ccbf3147df32', 'c224faa8-58bc-4ab5-a439-5220bba8f954', 'Objetivo 1', '2005-12-11');

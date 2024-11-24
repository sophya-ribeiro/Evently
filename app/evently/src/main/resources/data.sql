-- Active: 1725307864908@@127.0.0.1@5432@evently@public
INSERT INTO usuarios (id, nome, usuario, senha) VALUES
(1, 'Sophya Ribeiro', 'Organizador', '$2a$10$5wsiQ/W2ZlZblhSj8l11hOUSpTpOkcrG9GLE0hEjLaBlHxn65mXLW'),
(2, 'Vanessa Borges', 'Participante', '$2a$10$5wsiQ/W2ZlZblhSj8l11hOUSpTpOkcrG9GLE0hEjLaBlHxn65mXLW');

INSERT INTO organizadores (id, usuario_id, telefone, cnpj, email) VALUES (1, 1, '15461510000133', '67991364257', 'sophya@gmail.com');

INSERT INTO participantes (id, usuario_id) VALUES(1, 2);
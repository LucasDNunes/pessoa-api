DROP TABLE IF EXISTS pessoa;

CREATE TABLE pessoa (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  sexo VARCHAR(20) NOT NULL,
  email VARCHAR(250),
  data_nascimento timestamp,
  naturalidade VARCHAR(250),
  cpf VARCHAR(50) NOT NULL,
  created_date timestamp NOT NULL,
  modified_date timestamp  NOT NULL
);

INSERT INTO pessoa (nome, sexo, email, data_nascimento, naturalidade, cpf, created_date, modified_date) VALUES
  ('Lucas', 'MASCULINO', 'lucasdomingosnunes@gmail.com', '1996-02-07 19:10:25-07', 'Tubarão', '073.649.819-27', now(), now());
--   ('Bill', 'Gates', 'Billionaire Tech Entrepreneur'),
--   ('Folrunsho', 'Alakija', 'Billionaire Oil Magnate');
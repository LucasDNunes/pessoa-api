DROP TABLE IF EXISTS pessoa;

CREATE TABLE pessoa (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  sexo VARCHAR(20) NOT NULL,
  email VARCHAR(100),
  data_nascimento timestamp not null,
  naturalidade VARCHAR(100),
  cpf VARCHAR(15) NOT NULL UNIQUE,
  endereco VARCHAR(250),
  telefone INTEGER,
  created_date timestamp NOT NULL,
  modified_date timestamp  NOT NULL
--  UNIQUE KEY uk_cpf (cpf)
);

INSERT INTO pessoa (nome, sexo, email, data_nascimento, naturalidade, cpf, telefone, endereco, created_date, modified_date) VALUES
  ('Lucas', 'MASCULINO', 'lucasdomingosnunes@gmail.com', '1996-02-07 19:11:25-07', 'Tubarão', '11111111111', '12345678', 'endereco lucas', now(), now());

DROP TABLE IF EXISTS usuario;
CREATE TABLE usuario (
  username VARCHAR(100) PRIMARY KEY,
  password VARCHAR(100) NOT NULL,
  created_date timestamp NOT NULL,
  modified_date timestamp  NOT NULL
);


INSERT INTO usuario (username, password, created_date, modified_date) VALUES
  ('guest1', 'guest1', now(), now()),
  ('guest2', 'guest2', now(), now());

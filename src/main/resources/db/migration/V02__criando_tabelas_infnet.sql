--bloco
CREATE TABLE bloco(
  id BIGINT NOT NULL,
  descricao VARCHAR(50) NOT NULL
);

ALTER TABLE bloco ADD CONSTRAINT pk_id_bloco PRIMARY KEY (id);

--MODULO
CREATE TABLE modulo(
  id BIGINT NOT NULL,
  id_bloco BIGINT NOT NULL,
  descricao VARCHAR(50) NOT NULL
);

ALTER TABLE modulo ADD CONSTRAINT pk_id_modulo PRIMARY KEY (id);
ALTER TABLE modulo ADD CONSTRAINT fk_modu_id_bloco FOREIGN KEY (id_bloco) REFERENCES bloco (id);

--PROFESSOR
CREATE TABLE professor(
  id BIGINT NOT NULL,
  nome VARCHAR(100) NOT NULL
);
ALTER TABLE professor ADD CONSTRAINT pk_id_professor PRIMARY KEY (id);

--ALUNO
CREATE TABLE aluno(
  id BIGINT NOT NULL,
  email VARCHAR(120) NOT NULL,
  cpf char(11) UNIQUE NOT NULL,
  nome varchar(100) NOT NULL
);
ALTER TABLE aluno ADD CONSTRAINT pk_id_aluno PRIMARY KEY (id);

--TURMA
CREATE TABLE turma(
  id BIGINT NOT NULL,
  id_modulo BIGINT NOT NULL,
  id_professor BIGINT NOT NULL,
  numero VARCHAR(20) NOT NULL,
  data_termino DATETIME NOT NULL
);
ALTER TABLE turma ADD CONSTRAINT pk_id_turma PRIMARY KEY (id);
ALTER TABLE turma ADD CONSTRAINT fk_turm_id_modulo FOREIGN KEY (id_modulo) REFERENCES modulo (id);
ALTER TABLE turma ADD CONSTRAINT fk_turm_id_professor FOREIGN KEY (id_professor) REFERENCES professor (id);

--TURMA_ALUNO
CREATE TABLE turma_aluno(
  id_turma BIGINT NOT NULL,
  id_aluno BIGINT NOT NULL
);
ALTER TABLE turma_aluno ADD CONSTRAINT fk_tual_id_turma FOREIGN KEY (id_turma) REFERENCES turma (id);
ALTER TABLE turma_aluno ADD CONSTRAINT fk_tual_id_aluno FOREIGN KEY (id_aluno) REFERENCES aluno (id);
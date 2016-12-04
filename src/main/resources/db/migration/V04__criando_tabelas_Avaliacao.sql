CREATE TABLE template_avaliacao(
  id BIGINT NOT NULL,
  titulo VARCHAR(50) NOT NULL,
  situacao VARCHAR(3) NOT NULL
);


ALTER TABLE template_avaliacao ADD CONSTRAINT pk_id_template_avaliacao PRIMARY KEY (id);



--TEMPLATE_AVALIACAO_TURMA

CREATE TABLE template_avaliacao_turma(
  id_turma BIGINT NOT NULL,
  id_template_avaliacao BIGINT NOT NULL
);

ALTER TABLE template_avaliacao_turma ADD CONSTRAINT fk_tatu_id_turma FOREIGN KEY (id_turma) REFERENCES turma (id);

ALTER TABLE template_avaliacao_turma ADD CONSTRAINT fk_tatu_id_template_avaliacao FOREIGN KEY (id_template_avaliacao) REFERENCES template_avaliacao (id);



--TEMPLATE_TOPICO
CREATE TABLE template_topico(
  id BIGINT NOT NULL,
  enunciado VARCHAR(50) NOT NULL
);

ALTER TABLE template_topico ADD CONSTRAINT pk_id_template_topico PRIMARY KEY (id);


--TEMPLATE_AVALIACAO_TOPICO
CREATE TABLE template_avaliacao_topico(
  id_template_topico BIGINT NOT NULL,
  id_template_avaliacao BIGINT NOT NULL
);

ALTER TABLE template_avaliacao_topico ADD CONSTRAINT fk_teat_id_template_topico FOREIGN KEY (id_template_topico) REFERENCES template_topico (id);

ALTER TABLE template_avaliacao_topico ADD CONSTRAINT fk_teat_id_template_avaliacao FOREIGN KEY (id_template_avaliacao) REFERENCES template_avaliacao (id);


--TEMPLATE_PERGUNTA
CREATE TABLE template_pergunta(
  id BIGINT NOT NULL,
  questao VARCHAR(150) NOT NULL,
  id_template_topico BIGINT NOT NULL
);

ALTER TABLE template_pergunta ADD CONSTRAINT pk_id_template_pergunta PRIMARY KEY (id);

ALTER TABLE template_pergunta ADD CONSTRAINT fk_tepe_id_template_topico FOREIGN KEY (id_template_topico) REFERENCES template_topico (id);


--USUARIO
CREATE TABLE usuario(
  id BIGINT NOT NULL,
  nome VARCHAR(120) NOT NULL,
  login VARCHAR(50) NOT NULL,
  senha VARCHAR(10) NOT NULL,
  perfil VARCHAR(3) NOT NULL
);

ALTER TABLE usuario ADD CONSTRAINT pk_id_usuario PRIMARY KEY (id);


--AVALIACAO
CREATE TABLE avaliacao(
  id BIGINT NOT NULL,
  id_template_avaliacao BIGINT NOT NULL,
  id_turma BIGINT NOT NULL,
  id_aluno BIGINT NOT NULL
);

ALTER TABLE avaliacao ADD CONSTRAINT pk_id_avaliacao PRIMARY KEY (id);
ALTER TABLE avaliacao ADD CONSTRAINT fk_aval_id_template_avaliacao FOREIGN KEY (id_template_avaliacao) REFERENCES template_avaliacao (id);
ALTER TABLE avaliacao ADD CONSTRAINT fk_aval_id_turma FOREIGN KEY (id_turma) REFERENCES turma (id);
ALTER TABLE avaliacao ADD CONSTRAINT fk_aval_id_aluno FOREIGN KEY (id_aluno) REFERENCES aluno (id);


--RESPOSTA
CREATE TABLE resposta(
  id BIGINT NOT NULL,
  id_avaliacao BIGINT NOT NULL,
  valor TEXT NOT NULL,
  id_template_pergunta BIGINT NOT NULL
);

ALTER TABLE resposta ADD CONSTRAINT pk_id_resposta PRIMARY KEY (id);

ALTER TABLE resposta ADD CONSTRAINT fk_id_avaliacao FOREIGN KEY (id_avaliacao) REFERENCES avaliacao (id);

ALTER TABLE resposta ADD CONSTRAINT fk_resp_id_template_pergunta FOREIGN KEY (id_template_pergunta) REFERENCES template_pergunta (id);

CREATE TABLE template_pergunta(
  id BIGINT NOT NULL,
  questao VARCHAR(150) NOT NULL
);

ALTER TABLE template_pergunta ADD CONSTRAINT pk_id_template_pergunta PRIMARY KEY (id);
CREATE TABLE template_pergunta(
  id BIGINT NOT NULL,
  questao VARCHAR(150) NOT NULL,
  id_template_topico BIGINT NOT NULL
);

ALTER TABLE template_pergunta ADD CONSTRAINT pk_id_template_pergunta PRIMARY KEY (id);

ALTER TABLE template_pergunta ADD CONSTRAINT fk_tepe_id_template_topico FOREIGN KEY (id_template_topico) REFERENCES template_topico (id);
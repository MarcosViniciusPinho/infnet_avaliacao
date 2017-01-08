CREATE TABLE template_avaliacao_topico_pergunta(
  id BIGINT NOT NULL,
  id_template_topico BIGINT NOT NULL,
  id_template_avaliacao BIGINT NOT NULL,
  id_template_pergunta BIGINT NOT NULL
);
ALTER TABLE template_avaliacao_topico_pergunta ADD CONSTRAINT pk_id_template_avaliacao_topico_pergunta PRIMARY KEY (id);

ALTER TABLE template_avaliacao_topico_pergunta ADD CONSTRAINT fk_tatp_id_template_topico FOREIGN KEY (id_template_topico) REFERENCES template_topico (id);

ALTER TABLE template_avaliacao_topico_pergunta ADD CONSTRAINT fk_tatp_id_template_avaliacao FOREIGN KEY (id_template_avaliacao) REFERENCES template_avaliacao (id);

ALTER TABLE template_avaliacao_topico_pergunta ADD CONSTRAINT fk_tatp_id_template_pergunta FOREIGN KEY (id_template_pergunta) REFERENCES template_pergunta (id);
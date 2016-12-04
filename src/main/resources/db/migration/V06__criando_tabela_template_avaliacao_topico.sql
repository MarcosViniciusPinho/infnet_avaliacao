CREATE TABLE template_avaliacao_topico(
  id_template_topico BIGINT NOT NULL,
  id_template_avaliacao BIGINT NOT NULL
);

ALTER TABLE template_avaliacao_topico ADD CONSTRAINT fk_teat_id_template_topico FOREIGN KEY (id_template_topico) REFERENCES template_topico (id);

ALTER TABLE template_avaliacao_topico ADD CONSTRAINT fk_teat_id_template_avaliacao FOREIGN KEY (id_template_avaliacao) REFERENCES template_avaliacao (id);
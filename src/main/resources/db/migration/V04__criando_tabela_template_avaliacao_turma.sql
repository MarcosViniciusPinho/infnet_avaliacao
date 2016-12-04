CREATE TABLE template_avaliacao_turma(
  id_turma BIGINT NOT NULL,
  id_template_avaliacao BIGINT NOT NULL
);

ALTER TABLE template_avaliacao_turma ADD CONSTRAINT fk_tatu_id_turma FOREIGN KEY (id_turma) REFERENCES turma (id);

ALTER TABLE template_avaliacao_turma ADD CONSTRAINT fk_tatu_id_template_avaliacao FOREIGN KEY (id_template_avaliacao) REFERENCES template_avaliacao (id);
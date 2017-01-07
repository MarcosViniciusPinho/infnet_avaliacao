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
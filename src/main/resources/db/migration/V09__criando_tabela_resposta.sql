CREATE TABLE resposta(
  id BIGINT NOT NULL,
  id_avaliacao BIGINT NOT NULL,
  valor TEXT NOT NULL,
  id_template_pergunta BIGINT NOT NULL
);

ALTER TABLE resposta ADD CONSTRAINT pk_id_resposta PRIMARY KEY (id);

ALTER TABLE resposta ADD CONSTRAINT fk_id_avaliacao FOREIGN KEY (id_avaliacao) REFERENCES avaliacao (id);

ALTER TABLE resposta ADD CONSTRAINT fk_resp_id_template_pergunta FOREIGN KEY (id_template_pergunta) REFERENCES template_pergunta (id);
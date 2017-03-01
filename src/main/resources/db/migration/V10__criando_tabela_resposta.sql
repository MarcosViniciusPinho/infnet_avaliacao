CREATE TABLE resposta(
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  id_avaliacao BIGINT NOT NULL,
  valor TEXT,
  id_template_pergunta BIGINT NOT NULL
);

ALTER TABLE resposta ADD CONSTRAINT fk_id_avaliacao FOREIGN KEY (id_avaliacao) REFERENCES avaliacao (id);

ALTER TABLE resposta ADD CONSTRAINT fk_resp_id_template_pergunta FOREIGN KEY (id_template_pergunta) REFERENCES template_pergunta (id);
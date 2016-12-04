CREATE TABLE template_avaliacao(
  id BIGINT NOT NULL,
  titulo VARCHAR(50) NOT NULL,
  situacao VARCHAR(3) NOT NULL
);


ALTER TABLE template_avaliacao ADD CONSTRAINT pk_id_template_avaliacao PRIMARY KEY (id);
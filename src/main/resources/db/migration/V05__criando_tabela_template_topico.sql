CREATE TABLE template_topico(
  id BIGINT NOT NULL,
  enunciado VARCHAR(50) NOT NULL
);

ALTER TABLE template_topico ADD CONSTRAINT pk_id_template_topico PRIMARY KEY (id);
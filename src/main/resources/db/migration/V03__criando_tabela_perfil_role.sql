CREATE TABLE perfil_role(
  id_perfil BIGINT NOT NULL,
  id_role BIGINT NOT NULL
);

ALTER TABLE perfil_role ADD CONSTRAINT fk_pero_id_perfil FOREIGN KEY (id_perfil) REFERENCES perfil (id);

ALTER TABLE perfil_role ADD CONSTRAINT fk_pero_id_role FOREIGN KEY (id_role) REFERENCES role (id);
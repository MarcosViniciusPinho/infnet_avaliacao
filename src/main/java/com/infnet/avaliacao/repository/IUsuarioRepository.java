package com.infnet.avaliacao.repository;

import com.infnet.avaliacao.entity.Usuario;
import org.springframework.stereotype.Repository;

/**
 * Classe que serve para fazer as operações de CRUD para a tabela de usuario.
 */
@Repository
public interface IUsuarioRepository extends ICrudRepository<Usuario> {

    /**
     * Método que busca um usuario a partir de seu login
     * @param login login
     * @return Usuario
     */
    Usuario findByLogin(String login);

}
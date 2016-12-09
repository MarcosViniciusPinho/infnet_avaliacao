package com.infnet.avaliacao.persistence;

import com.infnet.avaliacao.entity.Usuario;

/**
 * Classe que serve para fazer as operações de CRUD para a tabela de usuario.
 */
public interface IUsuarioDAO extends ICrudDAO<Usuario> {

    /**
     * Método que busca um usuario a partir de seu login
     * @param login login
     * @return Usuario
     */
    Usuario findByLogin(String login);

}
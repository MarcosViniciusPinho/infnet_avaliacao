package com.infnet.avaliacao.persistence.impl;

import com.infnet.avaliacao.entity.Usuario;
import com.infnet.avaliacao.persistence.ICrudDao;

/**
 * Classe que serve para fazer as operações de CRUD para a tabela de usuario.
 */
public interface IUsuarioDao extends ICrudDao<Usuario> {

    /**
     * Método que busca um usuario a partir de seu login
     * @param login login
     * @return Usuario
     */
    Usuario findByLogin(String login);

}
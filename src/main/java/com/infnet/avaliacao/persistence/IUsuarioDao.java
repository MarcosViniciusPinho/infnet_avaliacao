package com.infnet.avaliacao.persistence;

import com.infnet.avaliacao.entity.Usuario;

public interface IUsuarioDao extends ICrudDao<Usuario>{

    /**
     * Método que busca um usuario a partir de seu login
     * @param login login
     * @return Usuario
     */
    Usuario findByLogin(String login);

}
package com.infnet.avaliacao.business.service;

import com.infnet.avaliacao.dto.impl.UsuarioDTO;
import com.infnet.avaliacao.entity.Usuario;

/**
 * Classe que representa o serviço de usuario.
 */
public interface IUsuarioService extends ICrudService<UsuarioDTO, Usuario> {

    /**
     * Método que exclui uma entidade de usuario.
     * @param id id
     */
    void delete(Long id);

}
package com.infnet.avaliacao.business.facade;

import com.infnet.avaliacao.dto.impl.UsuarioDTO;

/**
 * Classe que representa o facade de usuario.
 */
public interface UsuarioFacade extends CrudFacade<UsuarioDTO> {

    /**
     * Método que exclui uma entidade de usuario.
     * @param id id
     */
    void delete(Long id);

}

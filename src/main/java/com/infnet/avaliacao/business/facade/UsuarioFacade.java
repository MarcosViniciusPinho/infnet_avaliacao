package com.infnet.avaliacao.business.facade;

import com.infnet.avaliacao.dto.impl.UsuarioDTO;
import com.infnet.avaliacao.entity.Perfil;

import java.util.List;

/**
 * Classe que representa o facade de usuario.
 */
public interface UsuarioFacade extends CrudFacade<UsuarioDTO> {

    /**
     * Método que exclui uma entidade de usuario.
     * @param id id
     */
    void delete(Long id);

    List<Perfil> findAllPerfil();

    /**
     * Método que altera as informações do usuário que está logado na aplicação
     * @param usuarioDTO usuarioDTO
     */
    void updateUsuarioLogado(UsuarioDTO usuarioDTO);

}

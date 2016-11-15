package com.infnet.avaliacao.business.facade.impl;

import com.infnet.avaliacao.business.facade.IUsuarioFacade;
import com.infnet.avaliacao.business.service.IUsuarioService;
import com.infnet.avaliacao.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioFacade implements IUsuarioFacade {

    @Autowired
    private IUsuarioService usuarioService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Usuario entidade) {
        this.usuarioService.save(entidade);
    }

}
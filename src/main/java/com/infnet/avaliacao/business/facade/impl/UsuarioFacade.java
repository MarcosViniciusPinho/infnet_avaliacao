package com.infnet.avaliacao.business.facade.impl;

import com.infnet.avaliacao.business.facade.IUsuarioFacade;
import com.infnet.avaliacao.business.service.IUsuarioService;
import com.infnet.avaliacao.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioFacade implements IUsuarioFacade {

    @Autowired
    private IUsuarioService usuarioService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Usuario entity) {
        this.usuarioService.save(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Usuario findById(Long id) {
        return this.usuarioService.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        this.usuarioService.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Usuario> findAll() {
        return this.usuarioService.findAll();
    }

}
package com.infnet.avaliacao.business.facade.impl;

import com.infnet.avaliacao.business.facade.IUsuarioFacade;
import com.infnet.avaliacao.business.service.IUsuarioService;
import com.infnet.avaliacao.dto.UsuarioDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class UsuarioFacade implements IUsuarioFacade {

    @Resource
    private IUsuarioService usuarioService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(UsuarioDTO usuarioDTO) {
        this.usuarioService.save(usuarioDTO);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UsuarioDTO findById(Long id) {
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
    public List<UsuarioDTO> findAll() {
        return this.usuarioService.findAll();
    }

}
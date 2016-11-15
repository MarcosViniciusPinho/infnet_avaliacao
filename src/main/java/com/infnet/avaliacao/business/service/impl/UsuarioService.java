package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.business.service.IUsuarioService;
import com.infnet.avaliacao.entity.Usuario;
import com.infnet.avaliacao.persistence.IUsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioDao usuarioDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Usuario entity) {
        this.usuarioDao.save(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Usuario findById(Long id) {
        return this.usuarioDao.getOne(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        this.usuarioDao.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Usuario> findAll() {
        return this.usuarioDao.findAll();
    }

}
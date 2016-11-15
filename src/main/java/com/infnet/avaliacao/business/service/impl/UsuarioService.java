package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.business.service.IUsuarioService;
import com.infnet.avaliacao.entity.Usuario;
import com.infnet.avaliacao.persistence.IUsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioDao usuarioDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Usuario entidade) {
        this.usuarioDao.save(entidade);
    }

}
package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.business.service.PerfilService;
import com.infnet.avaliacao.entity.Perfil;
import com.infnet.avaliacao.repository.PerfilRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class PerfilServiceImpl implements PerfilService {

    @Resource
    private PerfilRepository perfilRepository;


    @Override
    public List<Perfil> findAll() {
        return this.perfilRepository.findAll();
    }

}
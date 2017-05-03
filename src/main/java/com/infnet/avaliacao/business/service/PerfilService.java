package com.infnet.avaliacao.business.service;

import com.infnet.avaliacao.entity.Perfil;

import java.util.List;

@FunctionalInterface
public interface PerfilService {

    List<Perfil> findAll();

}

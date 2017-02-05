package com.infnet.avaliacao.business.service;

import com.infnet.avaliacao.entity.Turma;

/**
 * Classe que representa o serviço de turma.
 */
public interface ITurmaService {

    /**
     * Método que busca uma turma a partir de seu id
     * @param id id
     * @return Turma
     */
    Turma findById(Long id);

    Long findTemplateAvaliacaoTurmaById(Long idTurma);

}
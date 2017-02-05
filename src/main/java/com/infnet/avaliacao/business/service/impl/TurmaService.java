package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.business.service.ITurmaService;
import com.infnet.avaliacao.entity.Turma;
import com.infnet.avaliacao.persistence.ITurmaDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * {@inheritDoc}
 */
@Service
public class TurmaService implements ITurmaService {

    @Resource
    private ITurmaDAO turmaDAO;

    /**
     * {@inheritDoc}
     */
    @Override
    public Turma findById(Long id) {
        return this.turmaDAO.getOne(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long findTemplateAvaliacaoTurmaById(Long idTurma){
        return this.turmaDAO.findByIdTurmaOnTemplateAvaliacaoTurma(idTurma);
    }

}
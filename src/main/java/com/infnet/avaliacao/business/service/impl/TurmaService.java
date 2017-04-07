package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.business.service.ITurmaService;
import com.infnet.avaliacao.entity.Turma;
import com.infnet.avaliacao.exception.NotFoundException;
import com.infnet.avaliacao.repository.TurmaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * {@inheritDoc}
 */
@Service
public class TurmaService implements ITurmaService {

    @Resource
    private TurmaRepository turmaRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Turma findById(Long id) {
        return this.turmaRepository.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long findTemplateAvaliacaoTurmaById(Long idTurma){
        return this.turmaRepository.findByIdTurmaOnTemplateAvaliacaoTurma(idTurma);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void verificarSeExisteTurma(Long id) {
        if(this.findById(id) == null){
            throw new NotFoundException("avaliacao.erro.turma.nao.encontrado");
        }
    }

}
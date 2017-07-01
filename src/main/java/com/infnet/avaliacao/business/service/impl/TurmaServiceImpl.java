package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.business.service.TurmaService;
import com.infnet.avaliacao.entity.Turma;
import com.infnet.avaliacao.exception.NotFoundException;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import com.infnet.avaliacao.repository.TurmaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * {@inheritDoc}
 */
@Service
public class TurmaServiceImpl implements TurmaService {

    @Resource
    private TurmaRepository turmaRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Turma findById(Long id) {
        ParameterExceptionUtil.validateObjectNull(id);
        return this.turmaRepository.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long findTemplateAvaliacaoTurmaById(Long idTurma){
        ParameterExceptionUtil.validateObjectNull(idTurma);
        return this.turmaRepository.findByIdTurmaOnTemplateAvaliacaoTurma(idTurma);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void verificarSeExisteTurma(Long id) {
        ParameterExceptionUtil.validateObjectNull(id);
        if(this.findById(id) == null){
            throw new NotFoundException("avaliacao.erro.turma.nao.encontrado");
        }
    }

}
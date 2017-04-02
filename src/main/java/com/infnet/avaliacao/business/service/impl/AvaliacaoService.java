package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.business.service.IAvaliacaoService;
import com.infnet.avaliacao.dto.impl.AvaliacaoDTO;
import com.infnet.avaliacao.entity.Aluno;
import com.infnet.avaliacao.entity.Avaliacao;
import com.infnet.avaliacao.entity.Turma;
import com.infnet.avaliacao.exception.UniqueException;
import com.infnet.avaliacao.repository.IAvaliacaoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * {@inheritDoc}
 */
@Service
public class AvaliacaoService implements IAvaliacaoService {

    @Resource
    private IAvaliacaoRepository avaliacaoRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Avaliacao save(AvaliacaoDTO avaliacaoDTO) {
        this.validate(avaliacaoDTO);
        return this.avaliacaoRepository.save(avaliacaoDTO.toEntity());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void verificarSeAlunoJaRespondeuAvaliacao(Turma turma, Aluno aluno){
        if(this.avaliacaoRepository.findByTurmaAndAluno(turma, aluno) != null){
            throw new UniqueException("avaliacao.erro.aluno.ja.respondeu");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(AvaliacaoDTO avaliacaoDTO) {}

}
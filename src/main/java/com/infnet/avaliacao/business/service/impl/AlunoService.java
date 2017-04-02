package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.business.service.IAlunoService;
import com.infnet.avaliacao.entity.Aluno;
import com.infnet.avaliacao.exception.NotFoundException;
import com.infnet.avaliacao.repository.IAlunoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * {@inheritDoc}
 */
@Service
public class AlunoService implements IAlunoService {

    @Resource
    private IAlunoRepository alunoRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public void verificarSeExisteCpf(Long cpf) {
        if(this.findByCpf(cpf) == null){
            throw new NotFoundException("avaliacao.erro.cpf.nao.encontrado");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Aluno findByCpf(Long cpf){
        return this.alunoRepository.findByCpf(cpf);
    }
}
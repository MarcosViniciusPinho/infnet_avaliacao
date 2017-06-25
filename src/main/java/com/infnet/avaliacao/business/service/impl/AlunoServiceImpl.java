package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.business.service.AlunoService;
import com.infnet.avaliacao.entity.Aluno;
import com.infnet.avaliacao.exception.NotFoundException;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import com.infnet.avaliacao.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * {@inheritDoc}
 */
@Service
public class AlunoServiceImpl implements AlunoService {

    @Resource
    private AlunoRepository alunoRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public void verificarSeExisteCpf(Long cpf) {
        ParameterExceptionUtil.validateObjectNull(cpf);
        if(this.findByCpf(cpf) == null){
            throw new NotFoundException("avaliacao.erro.cpf.nao.encontrado");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Aluno findByCpf(Long cpf){
        ParameterExceptionUtil.validateObjectNull(cpf);
        return this.alunoRepository.findByCpf(cpf);
    }
}
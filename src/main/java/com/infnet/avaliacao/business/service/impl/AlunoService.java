package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.business.service.IAlunoService;
import com.infnet.avaliacao.entity.Aluno;
import com.infnet.avaliacao.persistence.IAlunoDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * {@inheritDoc}
 */
@Service
public class AlunoService implements IAlunoService {

    @Resource
    private IAlunoDAO alunoDAO;

    /**
     * {@inheritDoc}
     */
    @Override
    public Aluno findByCpf(Long cpf) {
        return this.alunoDAO.findByCpf(cpf);
    }
}
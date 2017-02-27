package com.infnet.avaliacao.business.service;

import com.infnet.avaliacao.entity.Aluno;

/**
 * Classe que representa o serviço de aluno.
 */
public interface IAlunoService {

    void verificarSeExisteCpfDoAluno(Long cpf);

    /**
     * Método que busca um aluno a partir de seu cpf
     * @param cpf cpf
     * @return Aluno
     */
    Aluno findByCpf(Long cpf);

}
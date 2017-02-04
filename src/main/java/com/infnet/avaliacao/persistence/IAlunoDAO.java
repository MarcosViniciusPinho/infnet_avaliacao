package com.infnet.avaliacao.persistence;

import com.infnet.avaliacao.entity.Aluno;

/**
 * Classe que serve para fazer as operações de CRUD para a tabela de aluno.
 */
public interface IAlunoDAO extends ICrudDAO<Aluno>{

    /**
     * Método que busca um aluno a partir de seu cpf
     * @param cpf cpf
     * @return Aluno
     */
    Aluno findByCpf(Long cpf);

}

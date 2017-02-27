package com.infnet.avaliacao.persistence;

import com.infnet.avaliacao.entity.Aluno;
import com.infnet.avaliacao.entity.Avaliacao;
import com.infnet.avaliacao.entity.Turma;

/**
 * Classe que serve para fazer as operações de CRUD para a tabela de avaliação.
 */
public interface IAvaliacaoDAO extends ICrudDAO<Avaliacao> {

    /**
     * Método que verifica se um aluno já realizou a avaliação para aquela turma.
     * @param turma turma
     * @param aluno aluno
     * @return Avaliacao
     */
    Avaliacao findByTurmaAndAluno(Turma turma, Aluno aluno);

}
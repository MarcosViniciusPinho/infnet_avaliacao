package com.infnet.avaliacao.repository;

import com.infnet.avaliacao.entity.Aluno;
import com.infnet.avaliacao.entity.Avaliacao;
import com.infnet.avaliacao.entity.Turma;
import org.springframework.stereotype.Repository;

/**
 * Classe que serve para fazer as operações de CRUD para a tabela de avaliação.
 */
@Repository
public interface IAvaliacaoRepository extends ICrudRepository<Avaliacao> {

    /**
     * Método que verifica se um aluno já realizou a avaliação para aquela turma.
     * @param turma turma
     * @param aluno aluno
     * @return Avaliacao
     */
    Avaliacao findByTurmaAndAluno(Turma turma, Aluno aluno);

}
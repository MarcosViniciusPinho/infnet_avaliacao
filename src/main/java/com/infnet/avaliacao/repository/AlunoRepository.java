package com.infnet.avaliacao.repository;

import com.infnet.avaliacao.entity.Aluno;
import org.springframework.stereotype.Repository;

/**
 * Classe que serve para fazer as operações de CRUD para a tabela de aluno.
 */
@Repository
public interface AlunoRepository extends CrudRepository<Aluno> {

    /**
     * Método que busca um aluno a partir de seu cpf
     * @param cpf cpf
     * @return Aluno
     */
    Aluno findByCpf(Long cpf);

}

package com.infnet.avaliacao.repository;

import com.infnet.avaliacao.entity.Resposta;
import org.springframework.stereotype.Repository;

/**
 * Classe que serve para fazer as operações de CRUD para a tabela de resposta.
 */
@Repository
public interface RespostaRepository extends CrudRepository<Resposta> {
}
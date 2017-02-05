package com.infnet.avaliacao.persistence;

import com.infnet.avaliacao.entity.Turma;
import com.infnet.avaliacao.persistence.util.QueryConstant;
import org.springframework.data.jpa.repository.Query;

/**
 * Classe que serve para fazer as operações de CRUD para a tabela de turma.
 */
public interface ITurmaDAO extends ICrudDAO<Turma>{

    /**
     * Houve necessidade de criar um SQL pois o mapeamento do hibernate inviabilizou a busca de turmas associadas a tabela associativa
     * template_avaliacao_turma. Método que busca uma turma pelo seu determinado id associado a um template avaliacao.
     * @param idTurma idTurma
     * @return Long
     */
    @Query(value = QueryConstant.FIND_BY_ID_TURMA_ON_TEMPLATE_AVALIACAO_TURMA, nativeQuery = true)
    Long findByIdTurmaOnTemplateAvaliacaoTurma(Long idTurma);

}

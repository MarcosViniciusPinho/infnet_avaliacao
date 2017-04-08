package com.infnet.avaliacao.repository.util;

/**
 * Classe responsável por ser o repositório de script SQL em alguns lugares da aplicação.
 */
public class QueryConstant {

    public static final String FIND_BY_ID_TURMA_ON_TEMPLATE_AVALIACAO_TURMA = "select tatu.id_template_avaliacao from template_avaliacao_turma tatu " +
            "inner join turma turm on tatu.id_turma=turm.id where tatu.id_turma = ?1";

}
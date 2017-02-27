package com.infnet.avaliacao.business.service;

import com.infnet.avaliacao.dto.impl.AvaliacaoDTO;
import com.infnet.avaliacao.entity.Aluno;
import com.infnet.avaliacao.entity.Avaliacao;
import com.infnet.avaliacao.entity.Turma;

/**
 * Classe que representa o serviço de avaliacao.
 */
public interface IAvaliacaoService {

    /**
     * Método que salva/altera a entidade no banco.
     * @param avaliacaoDTO avaliacaoDTO
     * return Avaliacao
     */
    Avaliacao save(AvaliacaoDTO avaliacaoDTO);

    /**
     * Método que serve para fazer as validacoes
     * @param avaliacaoDTO avaliacaoDTO
     */
    void validate(AvaliacaoDTO avaliacaoDTO);

    void verificarSeAlunoJaRespondeuAvaliacao(Turma turma, Aluno aluno);
}
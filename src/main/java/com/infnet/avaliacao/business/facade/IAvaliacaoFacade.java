package com.infnet.avaliacao.business.facade;

import com.infnet.avaliacao.dto.impl.AvaliacaoDTO;

/**
 * Classe que representa o facade de avaliacao.
 */
public interface IAvaliacaoFacade {

    /**
     * Método que salva/altera a entidade no banco.
     * @param avaliacaoDTO avaliacaoDTO
     */
    void save(AvaliacaoDTO avaliacaoDTO);

    AvaliacaoDTO popularAlunoAndTurmaParaAvaliacao(Long cpf);

    boolean isExisteCpf(Long cpf);

}

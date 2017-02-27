package com.infnet.avaliacao.business.facade;

import com.infnet.avaliacao.dto.impl.AlunoDTO;
import com.infnet.avaliacao.dto.impl.AvaliacaoDTO;
import com.infnet.avaliacao.dto.impl.TurmaDTO;

/**
 * Classe que representa o facade de avaliacao.
 */
public interface IAvaliacaoFacade {

    /**
     * Método que salva/altera a entidade no banco.
     * @param avaliacaoDTO avaliacaoDTO
     */
    void save(AvaliacaoDTO avaliacaoDTO);

    AvaliacaoDTO popularAlunoAndTurmaParaAvaliacao(Long cpf, Long idTurma);

    void verificarSeAlunoJaRespondeuAvaliacao(TurmaDTO turmaDTO, AlunoDTO alunoDTO);

    void verificarParametrosEnviadosAoCarregarPagina(Long cpf, Long id);
}

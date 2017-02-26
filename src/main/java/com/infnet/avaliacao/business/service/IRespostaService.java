package com.infnet.avaliacao.business.service;

import com.infnet.avaliacao.dto.impl.AvaliacaoDTO;

/**
 * Classe que representa o serviço de resposta.
 */
public interface IRespostaService {

    /**
     * Método que recebe uma avaliacaoDTO para obter algumas informações então é criado uma lista de respostaDTO para aí sim serem persistidas na base.
     * @param avaliacaoDTO avaliacaoDTO
     */
    void save(AvaliacaoDTO avaliacaoDTO);

}
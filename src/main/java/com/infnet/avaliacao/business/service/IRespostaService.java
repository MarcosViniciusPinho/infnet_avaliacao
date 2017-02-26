package com.infnet.avaliacao.business.service;

import com.infnet.avaliacao.dto.impl.RespostaDTO;
import com.infnet.avaliacao.entity.Avaliacao;

import java.util.List;

/**
 * Classe que representa o serviço de resposta.
 */
public interface IRespostaService {

    /**
     * Método que salva a lista de entidade resposta no banco.
     * @param respostaDTOList respostaDTOList
     */
    void save(List<RespostaDTO> respostaDTOList);

    /**
     * Método responsável pela criação do objeto respostaDTO
     * @param resposta resposta
     * @param idTemplatePergunta idTemplatePergunta
     * @param avaliacao avaliacao
     * @return RespostaDTO
     */
    RespostaDTO popularResposta(String resposta, Long idTemplatePergunta, Avaliacao avaliacao);

}
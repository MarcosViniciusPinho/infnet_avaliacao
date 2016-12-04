package com.infnet.avaliacao.business.facade;

import com.infnet.avaliacao.dto.TemplateAvaliacaoDTO;

import java.util.List;

/**
 * Classe que representa o façade de template avaliacao.
 */
public interface ITemplateAvaliacaoFacade {

    /**
     * Método que lista todos os registros no banco de template avaliação.
     * @return List<TemplateAvaliacaoDTO>
     */
    List<TemplateAvaliacaoDTO> findAll();

    /**
     * Método que exclui um template avaliação.
     * @param id id
     */
    void delete(Long id);

    /**
     * Método que busca um template avaliacao a partir de seu id
     * @param id id
     * @return TemplateAvaliacaoDTO
     */
    TemplateAvaliacaoDTO findById(Long id);

}
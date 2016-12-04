package com.infnet.avaliacao.business.service;

import com.infnet.avaliacao.dto.TemplateAvaliacaoDTO;

import java.util.List;

/**
 * Classe que representa o serviço de template avaliacao.
 */
public interface ITemplateAvaliacaoService {

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



}
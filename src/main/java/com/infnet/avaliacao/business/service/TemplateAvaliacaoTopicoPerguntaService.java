package com.infnet.avaliacao.business.service;

import com.infnet.avaliacao.dto.impl.TemplateAvaliacaoDTO;
import com.infnet.avaliacao.dto.impl.TemplateAvaliacaoTopicoPerguntaDTO;
import com.infnet.avaliacao.dto.impl.TemplatePerguntaDTO;
import com.infnet.avaliacao.dto.impl.TemplateTopicoDTO;

import java.util.List;

/**
 * Classe que representa o serviço de TemplateAvaliacaoTopicoPergunta
 */
public interface TemplateAvaliacaoTopicoPerguntaService {

    void save(List<TemplateAvaliacaoTopicoPerguntaDTO> templateAvaliacaoTopicoPerguntaDTOList);

    /**
     * Método que cria o objeto da classe associativa, houve necessidade deste trecho de código pois
     * esta classe associativa tem 3 atributos e o hibernate tem dificuldade de gerenciar essa quantidade de atributos então a associacao ocorre
     * na mao mesmo.
     * @param templatePerguntaDTOList templatePerguntaDTOList
     * @param templateTopicoDTO templateTopicoDTO
     * @return List<TemplateAvaliacaoTopicoPerguntaDTO>
     */
    List<TemplateAvaliacaoTopicoPerguntaDTO> produceAssociativeClass(List<TemplatePerguntaDTO> templatePerguntaDTOList,
                                                                     TemplateTopicoDTO templateTopicoDTO,
                                                                     TemplateAvaliacaoDTO templateAvaliacaoDTO);

}
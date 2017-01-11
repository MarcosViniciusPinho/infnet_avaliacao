package com.infnet.avaliacao.business.service;

import com.infnet.avaliacao.dto.impl.TemplateAvaliacaoDTO;
import com.infnet.avaliacao.dto.impl.TemplatePerguntaDTO;
import com.infnet.avaliacao.dto.impl.TemplateTopicoDTO;
import com.infnet.avaliacao.entity.TemplatePergunta;

import java.util.List;

/**
 * Classe que representa o serviço de template pergunta.
 */
public interface ITemplatePerguntaService extends ICrudService<TemplatePerguntaDTO, TemplatePergunta> {

    /**
     * Recupera a lista de templates perguntas a partir de uma lista de seus respectivos ids.
     * @param idsTemplatePergunta idsTemplatePergunta
     * @return List<TemplatePergunta>
     */
    List<TemplatePergunta> getListaTemplatesPerguntasPorId(List<Long> idsTemplatePergunta);

    /**
     * Método que lista as perguntas com seu checkbox devidamente associado ao seu respectivo topico de uma avaliação.
     * @param templateTopicoDTO templateTopicoDTO
     * @param templateAvaliacaoDTO templateAvaliacaoDTO
     * @return List<TemplatePerguntaDTO>
     */
    List<TemplatePerguntaDTO> findAllComCheckedPerguntasMarcadas(TemplateTopicoDTO templateTopicoDTO,
                                                                 TemplateAvaliacaoDTO templateAvaliacaoDTO);

}
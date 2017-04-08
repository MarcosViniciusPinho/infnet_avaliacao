package com.infnet.avaliacao.business.facade;

import com.infnet.avaliacao.dto.impl.TemplateAvaliacaoDTO;
import com.infnet.avaliacao.dto.impl.TemplateAvaliacaoTopicoPerguntaDTO;
import com.infnet.avaliacao.dto.impl.TemplatePerguntaDTO;
import com.infnet.avaliacao.dto.impl.TemplateTopicoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Classe que representa o façade de template pergunta.
 */
public interface TemplatePerguntaFacade extends CrudFacade<TemplatePerguntaDTO> {

    /**
     * Recupera a lista de templates pergunta a partir de uma lista de seus respectivos ids.
     * @param idsTemplatePergunta idsTemplatePergunta
     * @return List<TemplatePerguntaDTO>
     */
    List<TemplatePerguntaDTO> getListaTemplatesPerguntasPorId(List<Long> idsTemplatePergunta);

    List<TemplateAvaliacaoTopicoPerguntaDTO> getListaPerguntasAssociadasAoTopicoPorAvaliacao(List<TemplatePerguntaDTO> templatePerguntaDTOList,
                                                                     TemplateTopicoDTO templateTopicoDTO,
                                                                     TemplateAvaliacaoDTO templateAvaliacaoDTO);

    /**
     * Método que lista as perguntas com seu checkbox devidamente associado ao seu respectivo topico de uma avaliação.
     * @param templateTopicoDTO templateTopicoDTO
     * @param templateAvaliacaoDTO templateAvaliacaoDTO
     * @param pageable pageable
     * @return Page<TemplatePerguntaDTO>
     */
    Page<TemplatePerguntaDTO> findAllComCheckedPerguntasMarcadas(TemplateTopicoDTO templateTopicoDTO,
                                                                 TemplateAvaliacaoDTO templateAvaliacaoDTO,
                                                                 Pageable pageable);

}
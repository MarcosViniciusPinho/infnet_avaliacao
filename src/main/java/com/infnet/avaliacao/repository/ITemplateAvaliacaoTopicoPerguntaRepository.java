package com.infnet.avaliacao.repository;

import com.infnet.avaliacao.entity.TemplateAvaliacao;
import com.infnet.avaliacao.entity.TemplateAvaliacaoTopicoPergunta;
import com.infnet.avaliacao.entity.TemplatePergunta;
import com.infnet.avaliacao.entity.TemplateTopico;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Classe que serve para fazer as operações de CRUD para a tabela associativa entre Template Avaliação, Template Tópico e Template Pergunta
 */
@Repository
public interface ITemplateAvaliacaoTopicoPerguntaRepository extends ICrudRepository<TemplateAvaliacaoTopicoPergunta> {

    TemplateAvaliacaoTopicoPergunta findByTemplateAvaliacaoAndTemplateTopicoAndTemplatePerguntaEquals(TemplateAvaliacao templateAvaliacao,
                                                                                                      TemplateTopico templateTopico,
                                                                                                      TemplatePergunta templatePergunta);

    List<TemplateAvaliacaoTopicoPergunta> findAllByTemplateAvaliacaoAndTemplateTopicoEquals(TemplateAvaliacao templateAvaliacao,
                                                                                                            TemplateTopico templateTopico);
}
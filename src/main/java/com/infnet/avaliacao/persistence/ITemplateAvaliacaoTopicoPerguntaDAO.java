package com.infnet.avaliacao.persistence;

import com.infnet.avaliacao.entity.TemplateAvaliacao;
import com.infnet.avaliacao.entity.TemplateAvaliacaoTopicoPergunta;
import com.infnet.avaliacao.entity.TemplatePergunta;
import com.infnet.avaliacao.entity.TemplateTopico;

/**
 * Classe que serve para fazer as operações de CRUD para a tabela associativa entre Template Avaliação, Template Tópico e Template Pergunta
 */
public interface ITemplateAvaliacaoTopicoPerguntaDAO extends ICrudDAO<TemplateAvaliacaoTopicoPergunta> {

    TemplateAvaliacaoTopicoPergunta findByTemplateAvaliacaoAndTemplateTopicoAndTemplatePerguntaEquals(TemplateAvaliacao templateAvaliacao,
                                                                                                      TemplateTopico templateTopico,
                                                                                                      TemplatePergunta templatePergunta);
}
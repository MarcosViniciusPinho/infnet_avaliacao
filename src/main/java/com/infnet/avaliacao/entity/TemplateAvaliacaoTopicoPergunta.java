package com.infnet.avaliacao.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Foi necessário criar esta classe para poder mapear esta entidade associativa pois para a mesma existem 3 atributos, portanto o hibernate
 * não gerencia esses atributos pela anotação @ManyToMany. Devido a este fator foi necessário fazer o relacionamento NxN manualmente.
 * Obs: Os atributos id e ativo só existem pois esta classe associativa está mapeada, pois se estivesse sido gerenciado pelo hibernate pela anotação
 * dito a cima estes atributos não existiriam.
 */
@Entity
@Table(name = "template_avaliacao_topico_pergunta")
public class TemplateAvaliacaoTopicoPergunta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_template_avaliacao", nullable = false)
    private TemplateAvaliacao templateAvaliacao;

    @ManyToOne
    @JoinColumn(name = "id_template_topico", nullable = false)
    private TemplateTopico templateTopico;

    @ManyToOne
    @JoinColumn(name = "id_template_pergunta", nullable = false)
    private TemplatePergunta templatePergunta;

    @Column(name = "ativo", nullable = false)
    private boolean ativo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TemplateAvaliacao getTemplateAvaliacao() {
        return templateAvaliacao;
    }

    public void setTemplateAvaliacao(TemplateAvaliacao templateAvaliacao) {
        this.templateAvaliacao = templateAvaliacao;
    }

    public TemplateTopico getTemplateTopico() {
        return templateTopico;
    }

    public void setTemplateTopico(TemplateTopico templateTopico) {
        this.templateTopico = templateTopico;
    }

    public TemplatePergunta getTemplatePergunta() {
        return templatePergunta;
    }

    public void setTemplatePergunta(TemplatePergunta templatePergunta) {
        this.templatePergunta = templatePergunta;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}

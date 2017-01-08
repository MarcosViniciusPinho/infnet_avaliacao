package com.infnet.avaliacao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "template_pergunta")
public class TemplatePergunta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "questao", length = 150, nullable = false)
    private String questao;

    @OneToMany(mappedBy = "templatePergunta", cascade = CascadeType.ALL)
    private List<TemplateAvaliacaoTopicoPergunta> templateAvaliacaoTopicoPerguntaList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestao() {
        return questao;
    }

    public void setQuestao(String questao) {
        this.questao = questao;
    }

    public List<TemplateAvaliacaoTopicoPergunta> getTemplateAvaliacaoTopicoPerguntaList() {
        return templateAvaliacaoTopicoPerguntaList;
    }

    public void setTemplateAvaliacaoTopicoPerguntaList(List<TemplateAvaliacaoTopicoPergunta> templateAvaliacaoTopicoPerguntaList) {
        this.templateAvaliacaoTopicoPerguntaList = templateAvaliacaoTopicoPerguntaList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TemplatePergunta that = (TemplatePergunta) o;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}

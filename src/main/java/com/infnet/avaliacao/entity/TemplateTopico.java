package com.infnet.avaliacao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "template_topico")
public class TemplateTopico implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "enunciado", length = 50, nullable = false)
    private String enunciado;

    @OneToMany(mappedBy = "templateTopico")
    private List<TemplateAvaliacaoTopicoPergunta> templateAvaliacaoTopicoPerguntaList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
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
        TemplateTopico that = (TemplateTopico) o;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}

package com.infnet.avaliacao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "template_avaliacao")
public class TemplateAvaliacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "titulo", length = 50, nullable = false)
    private String titulo;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "template_avaliacao_topico", joinColumns = {
            @JoinColumn(name = "id_template_avaliacao", nullable = false)
    }, inverseJoinColumns = {
            @JoinColumn(name = "id_template_topico", nullable = false)
    })
    private List<TemplateTopico> templateTopicoList = new ArrayList<>(0);

    @ManyToMany
    @JoinTable(name = "template_avaliacao_turma", joinColumns = {
            @JoinColumn(name = "id_template_avaliacao", nullable = false)
    }, inverseJoinColumns = {
            @JoinColumn(name = "id_turma", nullable = false)
    })
    private List<Turma> turmaList = new ArrayList<>(0);

    @OneToMany(mappedBy = "templateAvaliacao")
    private List<Avaliacao> avaliacaoList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<TemplateTopico> getTemplateTopicoList() {
        return templateTopicoList;
    }

    public void setTemplateTopicoList(List<TemplateTopico> templateTopicoList) {
        this.templateTopicoList = templateTopicoList;
    }

    public List<Turma> getTurmaList() {
        return turmaList;
    }

    public void setTurmaList(List<Turma> turmaList) {
        this.turmaList = turmaList;
    }

    public List<Avaliacao> getAvaliacaoList() {
        return avaliacaoList;
    }

    public void setAvaliacaoList(List<Avaliacao> avaliacaoList) {
        this.avaliacaoList = avaliacaoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TemplateAvaliacao that = (TemplateAvaliacao) o;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}

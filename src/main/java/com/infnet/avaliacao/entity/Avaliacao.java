package com.infnet.avaliacao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "avaliacao")
public class Avaliacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_template_avaliacao", nullable = false)
    private TemplateAvaliacao templateAvaliacao;

    @OneToMany(mappedBy = "avaliacao", cascade = CascadeType.ALL)
    private List<Resposta> respostaList;

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

    public List<Resposta> getRespostaList() {
        return respostaList;
    }

    public void setRespostaList(List<Resposta> respostaList) {
        this.respostaList = respostaList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avaliacao avaliacao = (Avaliacao) o;
        return id != null ? id.equals(avaliacao.id) : avaliacao.id == null;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}

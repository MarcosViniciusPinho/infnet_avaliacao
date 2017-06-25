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

    @ManyToOne
    @JoinColumn(name = "id_template_avaliacao", nullable = false)
    private TemplateAvaliacao templateAvaliacao;

    @OneToMany(mappedBy = "avaliacao")
    private List<Resposta> respostaList;

    @ManyToOne
    @JoinColumn(name = "id_turma", nullable = false)
    private Turma turma;

    @OneToOne
    @JoinColumn(name = "id_aluno", nullable = false)
    private Aluno aluno;

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

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Avaliacao other = (Avaliacao) o;
        return ((this.templateAvaliacao != null && other.templateAvaliacao != null) && this.templateAvaliacao.equals(other.templateAvaliacao))
                && ((this.turma != null && other.turma != null) && this.turma.equals(other.turma))
                && ((this.aluno != null && other.aluno != null) && this.aluno.equals(other.aluno));
    }

    @Override
    public int hashCode() {
        int result = this.templateAvaliacao != null ? templateAvaliacao.hashCode() : 0;
        result += this.turma != null ? turma.hashCode() : 0;
        result += this.aluno != null ? aluno.hashCode() : 0;
        return result;
    }
}

package com.infnet.avaliacao.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "resposta")
public class Resposta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "valor", nullable = false)
    private String valor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_avaliacao", nullable = false)
    private Avaliacao avaliacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resposta resposta = (Resposta) o;
        return id != null ? id.equals(resposta.id) : resposta.id == null;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}

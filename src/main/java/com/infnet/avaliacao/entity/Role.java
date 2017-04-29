package com.infnet.avaliacao.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Classe que representa a tabela Role no banco de dados.
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role usuario = (Role) o;
        return id != null ? !id.equals(usuario.id) : usuario.id != null;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
package com.infnet.avaliacao.entity;

import javax.persistence.*;
import java.io.Serializable;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TemplateAvaliacao that = (TemplateAvaliacao) o;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return titulo != null ? titulo.equals(that.titulo) : that.titulo == null;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}

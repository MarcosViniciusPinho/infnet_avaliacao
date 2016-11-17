package com.infnet.avaliacao.entity;


import com.infnet.avaliacao.entity.domain.PerfilEnum;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Classe que representa a tabela usuario no banco de dados.
 */
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", length = 120, nullable = false)
    private String nome;

    @Column(name = "login", length = 50, nullable = false, unique = true)
    private String login;

    @Column(name = "senha", length = 10, nullable = false)
    private String senha;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "perfil", length = 3, nullable = false)
    private PerfilEnum perfil;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public PerfilEnum getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilEnum perfil) {
        this.perfil = perfil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id != null ? !id.equals(usuario.id) : usuario.id != null;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
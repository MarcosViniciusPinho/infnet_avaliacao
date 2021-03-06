package com.infnet.avaliacao.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

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

    @NotBlank(message = "usuario.mensagem.erro.nome")
    @Column(name = "nome", length = 120, nullable = false)
    private String nome;

    @NotBlank(message = "usuario.mensagem.erro.login")
    @Column(name = "login", length = 50, nullable = false, unique = true)
    private String login;

    @NotBlank(message = "usuario.mensagem.erro.senha")
    @Column(name = "senha", length = 200, nullable = false)
    private String senha;

    @NotNull(message = "usuario.mensagem.erro.perfil")
    @OneToOne
    @JoinColumn(name = "id_perfil", nullable = false)
    private Perfil perfil;

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

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Usuario other = (Usuario) o;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return this.id != null ? id.hashCode() : 0;
    }
}
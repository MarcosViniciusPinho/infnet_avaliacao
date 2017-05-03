package com.infnet.avaliacao.entity;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Classe que representa a tabela aluno no banco de dados.
 */
@Entity
@Table(name = "aluno")
public class Aluno implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", length = 120, nullable = false, unique = true)
    private String email;

    @Column(name = "cpf", length = 12, nullable = false, unique = true)
    private Long cpf;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Aluno aluno = (Aluno) o;
        return id != null ? !id.equals(aluno.id) : aluno.id != null;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
package com.infnet.avaliacao.dto.impl;


import com.infnet.avaliacao.dto.IDTO;
import com.infnet.avaliacao.entity.Aluno;

public class AlunoDTO implements IDTO<Aluno> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String email;

    private Long cpf;

    private String nome;

    /**
     * {@inheritDoc}
     */
    @Override
    public Aluno toEntity() {
        Aluno aluno = new Aluno();
        aluno.setId(this.getId());
        aluno.setNome(this.getNome());
        aluno.setCpf(this.getCpf());
        aluno.setEmail(this.getEmail());
        return aluno;
    }

    /**
     * {@inheritDoc}
     */
    @Override
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

}
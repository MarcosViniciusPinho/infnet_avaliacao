package com.infnet.avaliacao.dto.impl;


import com.infnet.avaliacao.dto.DTO;
import com.infnet.avaliacao.entity.Aluno;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;

public class AlunoDTO implements DTO<Aluno> {

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
     * Método que converte uma entidade para um dto.
     * @param aluno aluno
     * @return AlunoDTO
     */
    public static AlunoDTO toDto(Aluno aluno){
        ParameterExceptionUtil.validateObjectNull(aluno);
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(aluno.getId());
        alunoDTO.setEmail(aluno.getEmail());
        alunoDTO.setCpf(aluno.getCpf());
        alunoDTO.setNome(aluno.getNome());
        return alunoDTO;
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

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        AlunoDTO other = (AlunoDTO) o;
        return (this.id != null && other.id != null) && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return this.id != null ? id.hashCode() : 0;
    }

}
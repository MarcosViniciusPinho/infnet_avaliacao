package com.infnet.avaliacao.dto.impl;

import com.infnet.avaliacao.dto.DTO;
import com.infnet.avaliacao.entity.Modulo;
import com.infnet.avaliacao.entity.Turma;

import java.util.List;

public class ModuloDTO implements DTO<Modulo> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String descricao;

    private List<Turma> turmaList;

    @Override
    public Modulo toEntity() {
        Modulo modulo = new Modulo();
        modulo.setId(this.getId());
        modulo.setDescricao(this.getDescricao());
        return modulo;
    }

    /**
     * Método que converte uma entidade para um dto.
     * @param modulo modulo
     * @return ModuloDTO
     */
    public static ModuloDTO toDto(Modulo modulo){
        ModuloDTO moduloDTO = new ModuloDTO();
        moduloDTO.setId(modulo.getId());
        moduloDTO.setDescricao(modulo.getDescricao());
        return moduloDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Turma> getTurmaList() {
        return turmaList;
    }

    public void setTurmaList(List<Turma> turmaList) {
        this.turmaList = turmaList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModuloDTO turma = (ModuloDTO) o;
        return id != null ? id.equals(turma.id) : turma.id == null;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}

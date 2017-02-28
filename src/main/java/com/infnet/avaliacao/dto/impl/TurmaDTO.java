package com.infnet.avaliacao.dto.impl;

import com.infnet.avaliacao.dto.IDTO;
import com.infnet.avaliacao.entity.Turma;

import java.util.List;

public class TurmaDTO implements IDTO<Turma> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String numero;

    private List<AvaliacaoDTO> avaliacaoDTOList;

    private ModuloDTO moduloDTO;

    /**
     * {@inheritDoc}
     */
    @Override
    public Turma toEntity() {
        Turma turma = new Turma();
        turma.setId(this.getId());
        turma.setNumero(this.getNumero());
        turma.setAvaliacaoList(
                AvaliacaoDTO.convertListDtoToListEntity(
                        this.getAvaliacaoDTOList()));
        turma.setModulo(this.getModuloDTO().toEntity());
        return turma;
    }

    /**
     * Método que converte uma entidade para um dto.
     * @param turma turma
     * @return TurmaDTO
     */
    public static TurmaDTO toDto(Turma turma){
        TurmaDTO turmaDTO = new TurmaDTO();
        turmaDTO.setId(turma.getId());
        turmaDTO.setNumero(turma.getNumero());
        turmaDTO.setModuloDTO(
                ModuloDTO.toDto(turma.getModulo()));
        return turmaDTO;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public List<AvaliacaoDTO> getAvaliacaoDTOList() {
        return avaliacaoDTOList;
    }

    public void setAvaliacaoDTOList(List<AvaliacaoDTO> avaliacaoDTOList) {
        this.avaliacaoDTOList = avaliacaoDTOList;
    }

    public ModuloDTO getModuloDTO() {
        return moduloDTO;
    }

    public void setModuloDTO(ModuloDTO moduloDTO) {
        this.moduloDTO = moduloDTO;
    }
}

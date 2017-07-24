package com.infnet.avaliacao.dto.impl;

import com.infnet.avaliacao.dto.DTO;
import com.infnet.avaliacao.entity.Turma;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TurmaDTO implements DTO<Turma> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String numero;

    private List<AvaliacaoDTO> avaliacaoDTOList;

    private ModuloDTO moduloDTO;

    private Date dataTermino;

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
        turma.setDataTermino(this.getDataTermino());
        return turma;
    }

    /**
     * Método que converte uma entidade para um dto.
     * @param turma turma
     * @return TurmaDTO
     */
    public static TurmaDTO toDto(Turma turma){
        ParameterExceptionUtil.validateObjectNull(turma);
        TurmaDTO turmaDTO = new TurmaDTO();
        turmaDTO.setId(turma.getId());
        turmaDTO.setNumero(turma.getNumero());
        turmaDTO.setModuloDTO(
                ModuloDTO.toDto(turma.getModulo()));
        turmaDTO.setDataTermino(turma.getDataTermino());
        return turmaDTO;
    }

    /**
     * Método que converte uma lista de entidade para uma lista de dto.
     * @param entities entities
     * @return List<TemplateAvaliacaoDTO>
     */
    public static List<TurmaDTO> convertListEntityToListDto(List<Turma> entities){
        List<TurmaDTO> lista = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(entities)){
            for(Turma turma : entities){
                lista.add(toDto(turma));
            }
        }
        return lista;
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

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        TurmaDTO other = (TurmaDTO) o;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return this.id != null ? id.hashCode() : 0;
    }
}

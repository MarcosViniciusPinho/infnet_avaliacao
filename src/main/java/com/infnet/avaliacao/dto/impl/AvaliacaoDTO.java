package com.infnet.avaliacao.dto.impl;

import com.infnet.avaliacao.dto.IDTO;
import com.infnet.avaliacao.entity.Avaliacao;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class AvaliacaoDTO implements IDTO<Avaliacao> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private TemplateAvaliacaoDTO templateAvaliacaoDTO;

    private List<RespostaDTO> respostaDTOList;

    private TurmaDTO turmaDTO;

    private AlunoDTO alunoDTO;

    /**
     * {@inheritDoc}
     */
    @Override
    public Avaliacao toEntity() {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setId(this.getId());
        avaliacao.setTemplateAvaliacao(this.getTemplateAvaliacaoDTO().toEntity());
        avaliacao.setRespostaList(
                RespostaDTO.convertListDtoToListEntity(this.getRespostaDTOList()));
        avaliacao.setTurma(this.getTurmaDTO().toEntity());
        avaliacao.setAluno(this.getAlunoDTO().toEntity());
        return avaliacao;
    }

    /**
     * Método que converte uma lista de dtos para uma lista de entidades.
     * @param dtos dtos
     * @return List<Avaliacao>
     */
    public static List<Avaliacao> convertListDtoToListEntity(List<AvaliacaoDTO> dtos){
        List<Avaliacao> lista = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(dtos)){
            for(AvaliacaoDTO avaliacaoDTO : dtos){
                lista.add(avaliacaoDTO.toEntity());
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

    public TemplateAvaliacaoDTO getTemplateAvaliacaoDTO() {
        return templateAvaliacaoDTO;
    }

    public void setTemplateAvaliacaoDTO(TemplateAvaliacaoDTO templateAvaliacaoDTO) {
        this.templateAvaliacaoDTO = templateAvaliacaoDTO;
    }

    public List<RespostaDTO> getRespostaDTOList() {
        return respostaDTOList;
    }

    public void setRespostaDTOList(List<RespostaDTO> respostaDTOList) {
        this.respostaDTOList = respostaDTOList;
    }

    public TurmaDTO getTurmaDTO() {
        return turmaDTO;
    }

    public void setTurmaDTO(TurmaDTO turmaDTO) {
        this.turmaDTO = turmaDTO;
    }

    public AlunoDTO getAlunoDTO() {
        return alunoDTO;
    }

    public void setAlunoDTO(AlunoDTO alunoDTO) {
        this.alunoDTO = alunoDTO;
    }
}

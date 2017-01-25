package com.infnet.avaliacao.dto.impl;

import com.infnet.avaliacao.dto.IDTO;
import com.infnet.avaliacao.entity.Resposta;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class RespostaDTO implements IDTO<Resposta> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String valor;

    private AvaliacaoDTO avaliacaoDTO;

    private TemplatePerguntaDTO templatePerguntaDTO;

    /**
     * {@inheritDoc}
     */
    @Override
    public Resposta toEntity() {
        Resposta resposta = new Resposta();
        resposta.setId(this.getId());
        resposta.setValor(this.getValor());
        resposta.setAvaliacao(
                this.getAvaliacaoDTO().toEntity());
        resposta.setTemplatePergunta(
                this.getTemplatePerguntaDTO().toEntity());
        return resposta;
    }

    /**
     * Método que converte uma lista de dtos para uma lista de entidades.
     * @param dtos dtos
     * @return List<Resposta>
     */
    public static List<Resposta> convertListDtoToListEntity(List<RespostaDTO> dtos){
        List<Resposta> lista = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(dtos)){
            for(RespostaDTO respostaDTO : dtos){
                lista.add(respostaDTO.toEntity());
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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public AvaliacaoDTO getAvaliacaoDTO() {
        return avaliacaoDTO;
    }

    public void setAvaliacaoDTO(AvaliacaoDTO avaliacaoDTO) {
        this.avaliacaoDTO = avaliacaoDTO;
    }

    public TemplatePerguntaDTO getTemplatePerguntaDTO() {
        return templatePerguntaDTO;
    }

    public void setTemplatePerguntaDTO(TemplatePerguntaDTO templatePerguntaDTO) {
        this.templatePerguntaDTO = templatePerguntaDTO;
    }
}

package com.infnet.avaliacao.dto.impl;

import com.infnet.avaliacao.dto.DTO;
import com.infnet.avaliacao.entity.Avaliacao;
import com.infnet.avaliacao.entity.Resposta;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RespostaDTO implements DTO<Resposta> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String valor;

    private Avaliacao avaliacao;

    private TemplatePerguntaDTO templatePerguntaDTO;

    /**
     * {@inheritDoc}
     */
    @Override
    public Resposta toEntity() {
        Resposta resposta = new Resposta();
        resposta.setId(this.getId());
        resposta.setValor(this.getValor());
        resposta.setAvaliacao(this.getAvaliacao());
        resposta.setTemplatePergunta(
                this.getTemplatePerguntaDTO().toEntity());
        return resposta;
    }

    /**
     * Método que converte uma entidade para um dto.
     * @param resposta resposta
     * @return RespostaDTO
     */
    public static RespostaDTO toDto(Resposta resposta){
        ParameterExceptionUtil.validateObjectNull(resposta);
        RespostaDTO respostaDTO = new RespostaDTO();
        respostaDTO.setId(resposta.getId());
        respostaDTO.setValor(resposta.getValor());
        respostaDTO.setAvaliacao(resposta.getAvaliacao());
        respostaDTO.setTemplatePerguntaDTO(
               TemplatePerguntaDTO.toDto(
                       resposta.getTemplatePergunta()));
        return respostaDTO;
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
     * Método que converte uma lista de entidade para uma lista de dto.
     * @param entities entities
     * @return List<RespostaDTO>
     */
    public static List<RespostaDTO> convertListEntityToListDto(List<Resposta> entities){
        List<RespostaDTO> lista = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(entities)){
            for(Resposta resposta : entities){
                lista.add(toDto(resposta));
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

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public TemplatePerguntaDTO getTemplatePerguntaDTO() {
        return templatePerguntaDTO;
    }

    public void setTemplatePerguntaDTO(TemplatePerguntaDTO templatePerguntaDTO) {
        this.templatePerguntaDTO = templatePerguntaDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        RespostaDTO other = (RespostaDTO) o;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return this.id != null ? id.hashCode() : 0;
    }
}

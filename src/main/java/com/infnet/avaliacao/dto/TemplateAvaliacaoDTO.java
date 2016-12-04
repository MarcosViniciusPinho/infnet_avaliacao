package com.infnet.avaliacao.dto;

import com.infnet.avaliacao.entity.TemplateAvaliacao;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TemplateAvaliacaoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String titulo;


    /**
     * Método que converte um dto para uma entidade.
     * @return TemplateAvaliacao
     */
    public TemplateAvaliacao toEntity(){
        TemplateAvaliacao templateAvaliacao = new TemplateAvaliacao();
        templateAvaliacao.setId(this.getId());
        templateAvaliacao.setTitulo(this.getTitulo());
        return templateAvaliacao;
    }

    /**
     * Método que converte uma entidade para um dto.
     * @param templateAvaliacao templateAvaliacao
     * @return TemplateAvaliacaoDTO
     */
    public static TemplateAvaliacaoDTO toDto(TemplateAvaliacao templateAvaliacao){
        ParameterExceptionUtil.validateObjectNull(templateAvaliacao);
        TemplateAvaliacaoDTO templateAvaliacaoDTO = new TemplateAvaliacaoDTO();
        templateAvaliacaoDTO.setId(templateAvaliacao.getId());
        templateAvaliacaoDTO.setTitulo(templateAvaliacao.getTitulo());
        return templateAvaliacaoDTO;
    }

    /**
     * Método que converte uma lista de entidade para uma lista de dto.
     * @param entities entities
     * @return List<TemplateAvaliacaoDTO>
     */
    public static List<TemplateAvaliacaoDTO> convertListEntityToListDto(List<TemplateAvaliacao> entities){
        List<TemplateAvaliacaoDTO> lista = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(entities)){
            for(TemplateAvaliacao templateAvaliacao : entities){
                lista.add(toDto(templateAvaliacao));
            }
        }
        return lista;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}

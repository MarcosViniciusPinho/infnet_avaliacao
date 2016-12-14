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

    private List<TemplateTopicoDTO> templateTopicoDTOList = new ArrayList<>(0);

    public TemplateAvaliacao toEntity(){
        TemplateAvaliacao templateAvaliacao = new TemplateAvaliacao();
        templateAvaliacao.setTitulo(this.getTitulo());
        templateAvaliacao.setTemplateTopicoList(TemplateTopicoDTO.convertListDtoToListEntity(this.getTemplateTopicoDTOList()));
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
        templateAvaliacaoDTO.setTemplateTopicoDTOList(
                TemplateTopicoDTO.convertListEntityToListDto(
                        templateAvaliacao.getTemplateTopicoList()));
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

    public List<TemplateTopicoDTO> getTemplateTopicoDTOList() {
        return templateTopicoDTOList;
    }

    public void setTemplateTopicoDTOList(List<TemplateTopicoDTO> templateTopicoDTOList) {
        this.templateTopicoDTOList = templateTopicoDTOList;
    }
}

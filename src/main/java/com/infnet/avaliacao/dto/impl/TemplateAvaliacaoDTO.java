package com.infnet.avaliacao.dto.impl;

import com.infnet.avaliacao.dto.IDTO;
import com.infnet.avaliacao.entity.TemplateAvaliacao;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class TemplateAvaliacaoDTO implements IDTO<TemplateAvaliacao> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String titulo;

    private List<TemplateTopicoDTO> templateTopicoDTOList = new ArrayList<>(0);

    private List<Long> idsTemplateTopicoSelecionados = new ArrayList<>(0);

    @Override
    public TemplateAvaliacao toEntity(){
        TemplateAvaliacao templateAvaliacao = new TemplateAvaliacao();
        templateAvaliacao.setId(this.getId());
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

    /**
     * Método que carrega os topicos cadastrados para uma determinada avaliação e os carrega na tela de topicos nos checkbox's.
     * @return TemplateAvaliacaoDTO
     */
    public TemplateAvaliacaoDTO carregarTopicosCadastradosParaFicarSelecionados(){
        List<Long> templateTopicoList = new ArrayList<>();
        for(TemplateTopicoDTO templateTopicoDTO : this.getTemplateTopicoDTOList()){
            templateTopicoList.add(templateTopicoDTO.getId());
        }
        this.setIdsTemplateTopicoSelecionados(templateTopicoList);
        return this;
    }

    @Override
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

    public List<Long> getIdsTemplateTopicoSelecionados() {
        return idsTemplateTopicoSelecionados;
    }

    public void setIdsTemplateTopicoSelecionados(List<Long> idsTemplateTopicoSelecionados) {
        this.idsTemplateTopicoSelecionados = idsTemplateTopicoSelecionados;
    }
}
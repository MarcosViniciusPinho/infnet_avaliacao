package com.infnet.avaliacao.dto.impl;

import com.infnet.avaliacao.dto.IDTO;
import com.infnet.avaliacao.entity.TemplateTopico;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class TemplateTopicoDTO implements IDTO<TemplateTopico> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String enunciado;

    private List<TemplateAvaliacaoTopicoPerguntaDTO> templateAvaliacaoTopicoPerguntaDTOList;

    private List<Long> idsTemplatePerguntaSelecionados = new ArrayList<>(0);

    /**
     * {@inheritDoc}
     */
    @Override
    public TemplateTopico toEntity(){
        TemplateTopico templateTopico = new TemplateTopico();
        templateTopico.setId(this.getId());
        templateTopico.setEnunciado(this.getEnunciado());
        templateTopico.setTemplateAvaliacaoTopicoPerguntaList(
                TemplateAvaliacaoTopicoPerguntaDTO.convertListDtoToListEntity(
                        this.getTemplateAvaliacaoTopicoPerguntaDTOList()));
        return templateTopico;
    }

    /**
     * Método que converte uma entidade para um dto.
     * @param templateTopico templateTopico
     * @return TemplateTopicoDTO
     */
    public static TemplateTopicoDTO toDto(TemplateTopico templateTopico){
        ParameterExceptionUtil.validateObjectNull(templateTopico);
        TemplateTopicoDTO templateTopicoDTO = new TemplateTopicoDTO();
        templateTopicoDTO.setId(templateTopico.getId());
        templateTopicoDTO.setEnunciado(templateTopico.getEnunciado());
        templateTopicoDTO.setTemplateAvaliacaoTopicoPerguntaDTOList(
                TemplateAvaliacaoTopicoPerguntaDTO.convertListEntityToListDto(
                        templateTopico.getTemplateAvaliacaoTopicoPerguntaList()));
        return templateTopicoDTO;
    }

    /**
     * Método que converte uma lista de entidade para uma lista de dto.
     * @param entities entities
     * @return List<TemplateTopicoDTO>
     */
    public static List<TemplateTopicoDTO> convertListEntityToListDto(List<TemplateTopico> entities){
        List<TemplateTopicoDTO> lista = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(entities)){
            for(TemplateTopico templateTopico : entities){
                lista.add(toDto(templateTopico));
            }
        }
        return lista;
    }

    /**
     * Método que converte uma lista de dtos para uma lista de entidades.
     * @param dtos dtos
     * @return List<TemplateTopico>
     */
    public static List<TemplateTopico> convertListDtoToListEntity(List<TemplateTopicoDTO> dtos){
        List<TemplateTopico> lista = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(dtos)){
            for(TemplateTopicoDTO templateTopico : dtos){
                lista.add(templateTopico.toEntity());
            }
        }
        return lista;
    }

    /**
     * Método que carrega os topicos cadastrados para uma determinada avaliação e os carrega na tela de topicos nos checkbox's.
     * @return TemplateTopicoDTO
     */
    public TemplateTopicoDTO carregarPerguntasCadastradosParaFicarSelecionados(){
        List<Long> templatePerguntaList = new ArrayList<>();
        for(TemplateAvaliacaoTopicoPerguntaDTO templateAvaliacaoTopicoPerguntaDTO : this.getTemplateAvaliacaoTopicoPerguntaDTOList()){
            templatePerguntaList.add(templateAvaliacaoTopicoPerguntaDTO.getId());
        }
        this.setIdsTemplatePerguntaSelecionados(templatePerguntaList);
        return this;
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

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public List<TemplateAvaliacaoTopicoPerguntaDTO> getTemplateAvaliacaoTopicoPerguntaDTOList() {
        return templateAvaliacaoTopicoPerguntaDTOList;
    }

    public void setTemplateAvaliacaoTopicoPerguntaDTOList(List<TemplateAvaliacaoTopicoPerguntaDTO> templateAvaliacaoTopicoPerguntaDTOList) {
        this.templateAvaliacaoTopicoPerguntaDTOList = templateAvaliacaoTopicoPerguntaDTOList;
    }

    public List<Long> getIdsTemplatePerguntaSelecionados() {
        return idsTemplatePerguntaSelecionados;
    }

    public void setIdsTemplatePerguntaSelecionados(List<Long> idsTemplatePerguntaSelecionados) {
        this.idsTemplatePerguntaSelecionados = idsTemplatePerguntaSelecionados;
    }
}

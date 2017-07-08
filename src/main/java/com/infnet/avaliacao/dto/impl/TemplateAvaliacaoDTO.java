package com.infnet.avaliacao.dto.impl;

import com.infnet.avaliacao.controller.wrapper.PerguntaAssociadaWrapper;
import com.infnet.avaliacao.dto.DTO;
import com.infnet.avaliacao.entity.TemplateAvaliacao;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class TemplateAvaliacaoDTO implements DTO<TemplateAvaliacao> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String titulo;

    private List<TemplateTopicoDTO> templateTopicoDTOList = new ArrayList<>(0);

    private List<Long> idsTemplateTopicoSelecionados = new ArrayList<>(0);

    /**
     * {@inheritDoc}
     */
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
     * Método que converte uma lista de entidade para uma lista de dto.
     * @param entities entities
     * @param pageable pageable
     * @return Page<TemplateAvaliacaoDTO>
     */
    public static Page<TemplateAvaliacaoDTO> convertPageEntityToPageDto(Page<TemplateAvaliacao> entities, Pageable pageable){
        List<TemplateAvaliacaoDTO> lista = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(entities.getContent())){
            for(TemplateAvaliacao templateAvaliacao : entities.getContent()){
                lista.add(toDto(templateAvaliacao));
            }
        }
        return new PageImpl<>(lista, pageable, entities.getTotalElements());
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

    /**
     * Método que verifica se o id do TemplateAvaliacao é igual ao id do TemplateAvaliacao que é atributo da classe TemplateAvaliacaoTopicoPergunta,
     * verifica também se o id do TemplateTopico é igual ao id do TemplateTopico que é atributo da classe TemplateAvaliacaoTopicoPergunta e
     * Verifica se para aquela determinado pergunta está ativa na classe TemplateAvaliacaoTopicoPergunta.
     * @param templateTopicoDTO templateTopicoDTO
     * @param templateAvaliacaoTopicoPerguntaDTO templateAvaliacaoTopicoPerguntaDTO
     * @return boolean
     */
    protected boolean isVerificaTopicoAndAvaliacaoAndAtivo(TemplateTopicoDTO templateTopicoDTO,
                                                         TemplateAvaliacaoTopicoPerguntaDTO templateAvaliacaoTopicoPerguntaDTO){
        return templateAvaliacaoTopicoPerguntaDTO.isAtivo()
                && templateTopicoDTO.getId().equals(templateAvaliacaoTopicoPerguntaDTO.getTemplateTopico().getId())
                && this.getId().equals(templateAvaliacaoTopicoPerguntaDTO.getTemplateAvaliacao().getId());
    }

    protected void addPerguntasAssociadas(TemplateAvaliacaoTopicoPerguntaDTO templateAvaliacaoTopicoPerguntaDTO,
                                        List<TemplatePerguntaDTO> templatePerguntaDTOList, TemplateTopicoDTO templateTopicoDTO){
        if(this.isVerificaTopicoAndAvaliacaoAndAtivo(templateTopicoDTO, templateAvaliacaoTopicoPerguntaDTO)){
            templatePerguntaDTOList.add(
                    TemplatePerguntaDTO.toDto(
                            templateAvaliacaoTopicoPerguntaDTO.getTemplatePergunta()));
        }
    }

    private void percorrerPerguntasAssociadas(TemplateTopicoDTO templateTopicoDTO, List<TemplatePerguntaDTO> templatePerguntaDTOList){
        for(TemplateAvaliacaoTopicoPerguntaDTO templateAvaliacaoTopicoPerguntaDTO : templateTopicoDTO.getTemplateAvaliacaoTopicoPerguntaDTOList()){
            this.addPerguntasAssociadas(templateAvaliacaoTopicoPerguntaDTO, templatePerguntaDTOList, templateTopicoDTO);
        }
    }

    protected void init(List<PerguntaAssociadaWrapper> perguntaAssociadaWrapperList, TemplateTopicoDTO templateTopicoDTO){
        PerguntaAssociadaWrapper perguntaAssociadaWrapper = new PerguntaAssociadaWrapper();
        perguntaAssociadaWrapper.setTemplateTopicoDTO(templateTopicoDTO);
        List<TemplatePerguntaDTO> templatePerguntaDTOList = new ArrayList<>();
        this.percorrerPerguntasAssociadas(templateTopicoDTO, templatePerguntaDTOList);
        perguntaAssociadaWrapper.setTemplatePerguntaDTOList(templatePerguntaDTOList);
        perguntaAssociadaWrapperList.add(perguntaAssociadaWrapper);
    }

    /**
     * Método que serve para fazer a exibição dos topicos com perguntas associadas na tela de detalhar.
     * @param perguntaAssociadaWrapperList perguntaAssociadaWrapperList
     */
    public void detail(List<PerguntaAssociadaWrapper> perguntaAssociadaWrapperList){
        for(TemplateTopicoDTO templateTopicoDTO : this.getTemplateTopicoDTOList()){
            this.init(perguntaAssociadaWrapperList, templateTopicoDTO);
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        TemplateAvaliacaoDTO other = (TemplateAvaliacaoDTO) o;
        return (this.id != null && other.id != null) && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return this.id != null ? id.hashCode() : 0;
    }
}

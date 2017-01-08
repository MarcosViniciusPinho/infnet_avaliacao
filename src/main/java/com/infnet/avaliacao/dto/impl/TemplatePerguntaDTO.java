package com.infnet.avaliacao.dto.impl;

import com.infnet.avaliacao.dto.IDTO;
import com.infnet.avaliacao.dto.domain.MultiplaEscolhaEnum;
import com.infnet.avaliacao.entity.TemplatePergunta;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TemplatePerguntaDTO implements IDTO<TemplatePergunta> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String questao;

    private List<TemplateAvaliacaoTopicoPerguntaDTO> templateAvaliacaoTopicoPerguntaDTOList;

    private List<Long> idsTemplatePerguntaSelecionados = new ArrayList<>(0);

    /**
     * {@inheritDoc}
     */
    @Override
    public TemplatePergunta toEntity(){
        TemplatePergunta templatePergunta = new TemplatePergunta();
        templatePergunta.setId(this.getId());
        templatePergunta.setQuestao(this.getQuestao());
        templatePergunta.setTemplateAvaliacaoTopicoPerguntaList(
                TemplateAvaliacaoTopicoPerguntaDTO.convertListDtoToListEntity(
                        this.getTemplateAvaliacaoTopicoPerguntaDTOList()));
        return templatePergunta;
    }

    /**
     * Método que converte uma entidade para um dto.
     * @param templatePergunta templatePergunta
     * @return TemplatePerguntaDTO
     */
    public static TemplatePerguntaDTO toDto(TemplatePergunta templatePergunta){
        ParameterExceptionUtil.validateObjectNull(templatePergunta);
        TemplatePerguntaDTO templatePerguntaDTO = new TemplatePerguntaDTO();
        templatePerguntaDTO.setId(templatePergunta.getId());
        templatePerguntaDTO.setQuestao(templatePergunta.getQuestao());
        templatePerguntaDTO.setTemplateAvaliacaoTopicoPerguntaDTOList(
                TemplateAvaliacaoTopicoPerguntaDTO.convertListEntityToListDto(
                        templatePergunta.getTemplateAvaliacaoTopicoPerguntaList()));
        return templatePerguntaDTO;
    }

    /**
     * Método que converte uma lista de entidade para uma lista de dto.
     * @param entities entities
     * @return List<TemplatePerguntaDTO>
     */
    public static List<TemplatePerguntaDTO> convertListEntityToListDto(List<TemplatePergunta> entities){
        List<TemplatePerguntaDTO> lista = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(entities)){
            for(TemplatePergunta templatePergunta : entities){
                lista.add(toDto(templatePergunta));
            }
        }
        return lista;
    }

    /**
     * Método que carrega os topicos cadastrados para uma determinada avaliação e os carrega na tela de topicos nos checkbox's.
     * @return TemplatePerguntaDTO
     */
    public TemplatePerguntaDTO carregarPerguntasCadastradosParaFicarSelecionados(){
        List<Long> templatePerguntaList = new ArrayList<>();
        for(TemplateAvaliacaoTopicoPerguntaDTO templateAvaliacaoTopicoPerguntaDTO : this.getTemplateAvaliacaoTopicoPerguntaDTOList()){
            templatePerguntaList.add(templateAvaliacaoTopicoPerguntaDTO.getId());
        }
        this.setIdsTemplatePerguntaSelecionados(templatePerguntaList);
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestao() {
        return questao;
    }

    public void setQuestao(String questao) {
        this.questao = questao;
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

    /**
     * Exibe as opções de múltipla escolha para as peguntas
     * @return List<MultiplaEscolhaEnum>
     */
    public List<MultiplaEscolhaEnum> getMultiplaEscolhaEnumList() {
        return Arrays.asList(MultiplaEscolhaEnum.values());
    }

}

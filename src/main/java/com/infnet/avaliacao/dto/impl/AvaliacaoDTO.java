package com.infnet.avaliacao.dto.impl;

import com.infnet.avaliacao.controller.wrapper.PerguntaAssociadaWrapper;
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

    private int indiceTopico;

    private int totalTemplateTopicos;

    private List<String> respostasSelecionadasComPerguntas;

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

    public boolean isExisteTemplateAvaliacaoAndTemplateTopico(){
        return this.getTemplateAvaliacaoDTO() != null
                && CollectionUtils.isNotEmpty(this.getTemplateAvaliacaoDTO().getTemplateTopicoDTOList());
    }

    public void showNextTopicoComPergunta(List<PerguntaAssociadaWrapper> perguntaAssociadaWrapperList, int posicao){
        if(this.isExisteTemplateAvaliacaoAndTemplateTopico()){
            TemplateTopicoDTO templateTopicoDTO = this.getTemplateAvaliacaoDTO().getTemplateTopicoDTOList().get(posicao);
            this.getTemplateAvaliacaoDTO().init(perguntaAssociadaWrapperList, templateTopicoDTO);
        }
    }

    public boolean isExisteProximoTopico(){
        return this.getTemplateAvaliacaoDTO().getTemplateTopicoDTOList().size() > this.getIndiceTopico();
    }

    public String[] getRespostasAndPerguntasSeparados(String respostaComPerguntaAssociada){
        return respostaComPerguntaAssociada.split("-");
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

    public int getIndiceTopico() {
        return indiceTopico;
    }

    public void setIndiceTopico(int indiceTopico) {
        this.indiceTopico = indiceTopico;
    }

    public int getTotalTemplateTopicos() {
        return totalTemplateTopicos;
    }

    public void setTotalTemplateTopicos(int totalTemplateTopicos) {
        this.totalTemplateTopicos = totalTemplateTopicos;
    }

    public List<String> getRespostasSelecionadasComPerguntas() {
        return respostasSelecionadasComPerguntas;
    }

    public void setRespostasSelecionadasComPerguntas(List<String> respostasSelecionadasComPerguntas) {
        this.respostasSelecionadasComPerguntas = respostasSelecionadasComPerguntas;
    }
}

package com.infnet.avaliacao.dto.impl;

import com.infnet.avaliacao.controller.wrapper.PerguntaAssociadaWrapper;
import com.infnet.avaliacao.dto.DTO;
import com.infnet.avaliacao.dto.util.ConversorBinarioUtil;
import com.infnet.avaliacao.entity.Avaliacao;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class AvaliacaoDTO implements DTO<Avaliacao> {

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
     * Método que converte uma entidade para um dto.
     * @param avaliacao avaliacao
     * @return AvaliacaoDTO
     */
    public static AvaliacaoDTO toDto(Avaliacao avaliacao){
        ParameterExceptionUtil.validateObjectNull(avaliacao);
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
        avaliacaoDTO.setId(avaliacao.getId());
        avaliacaoDTO.setTemplateAvaliacaoDTO(
                TemplateAvaliacaoDTO.toDto(avaliacao.getTemplateAvaliacao()));
        avaliacaoDTO.setRespostaDTOList(
                RespostaDTO.convertListEntityToListDto(
                        avaliacao.getRespostaList()));
        avaliacaoDTO.setTurmaDTO(
               TurmaDTO.toDto(avaliacao.getTurma()));
        avaliacaoDTO.setAlunoDTO(
                AlunoDTO.toDto(avaliacao.getAluno()));
        return avaliacaoDTO;

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
     * Método que converte uma lista de entidade para uma lista de dto.
     * @param entities entities
     * @return List<AvaliacaoDTO>
     */
    public static List<AvaliacaoDTO> convertListEntityToListDto(List<Avaliacao> entities){
        List<AvaliacaoDTO> lista = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(entities)){
            for(Avaliacao avaliacao : entities){
                lista.add(toDto(avaliacao));
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

    private String[] getRespostasAndPerguntasSeparados(String respostaComPerguntaAssociada){
        return respostaComPerguntaAssociada.split("-");
    }

    public String getValorResposta(String respostaComPerguntaAssociada){
        String resposta = this.getRespostasAndPerguntasSeparados(
                respostaComPerguntaAssociada)[0];
        return ConversorBinarioUtil.converterBinaryToCharacterInString(resposta);
    }

    public Long getIdTemplatePergunta(String respostaComPerguntaAssociada){
        return Long.parseLong(this.getRespostasAndPerguntasSeparados(
                        respostaComPerguntaAssociada)[1]);
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

package com.infnet.avaliacao.controller;

import com.infnet.avaliacao.business.facade.AvaliacaoFacade;
import com.infnet.avaliacao.controller.util.*;
import com.infnet.avaliacao.controller.wrapper.PerguntaAssociadaWrapper;
import com.infnet.avaliacao.dto.impl.AvaliacaoDTO;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pela captura das informações na view.
 */
@Controller
@RequestMapping(value = PathConstant.PATH_RESPOSTA_AVALIACAO)
public class RespostaAvaliacaoController {

    @Resource
    private AvaliacaoFacade avaliacaoFacade;

    /**
     * Método que salva/altera uma entidade.
     * @param dto dto
     * @return String
     */
    @RequestMapping(value = ActionConstant.ACTION_SAVE, method = RequestMethod.POST)
    public String save(AvaliacaoDTO dto, Model model){
        ParameterExceptionUtil.validateObjectNull(dto);
        ParameterExceptionUtil.validateObjectNull(model);
        try{
            AvaliacaoDTO avaliacaoDTO = this.getFacade().popularAlunoAndTurmaParaAvaliacao(dto.getAlunoDTO().getCpf(),
                    dto.getTurmaDTO().getId());
            this.onForm(dto, avaliacaoDTO, model);
            if(this.isVerificarIndiceAtualComTamanhoTotalDaListaDeTopico(avaliacaoDTO)){
                this.buscarProximoTopicoComPerguntas(avaliacaoDTO, model);
            } else{
                this.getFacade().save(avaliacaoDTO);
                return getRedirectViewAgradecimento();
            }
        } catch (RuntimeException ex) {//NOSONAR a exceção é direcionada para a camada de visão da aplicação, portanto o uso do 'throw' neste ponto não é utilizado.
            model.addAttribute(MessageConstant.ERROR, ex.getLocalizedMessage());
        }
        return getViewForm();
    }

    private void onForm(AvaliacaoDTO dto, AvaliacaoDTO avaliacaoDTO, Model model){
        avaliacaoDTO.setIndiceTopico(dto.getIndiceTopico());
        avaliacaoDTO.setTotalTemplateTopicos(avaliacaoDTO.getTemplateAvaliacaoDTO().getTemplateTopicoDTOList().size());
        avaliacaoDTO.setRespostasSelecionadasComPerguntas(dto.getRespostasSelecionadasComPerguntas());
        int calculoParaBotaoProximo = (avaliacaoDTO.getTotalTemplateTopicos()-1) - avaliacaoDTO.getIndiceTopico();
        model.addAttribute(ApplicationConstant.EXIBIR_BOTAO_PROXIMO, calculoParaBotaoProximo > 0);
        model.addAttribute(ApplicationConstant.EXIBIR_BOTAO_SALVAR, calculoParaBotaoProximo == 0);
    }

    /**
     * Método que redireciona o usuário para a tela de responder avaliacao.
     * @param model model
     * @return ModelAndView
     */
    @RequestMapping(value = ActionConstant.ACTION_LINK_RESPONDER_AVALIACAO)
    public String prepareCreate(@PathVariable Long cpf, @PathVariable Long id, Model model){
        try{
            this.getFacade().verificarParametrosEnviadosAoCarregarPagina(cpf, id);
            AvaliacaoDTO avaliacaoDTO = this.getFacade().popularAlunoAndTurmaParaAvaliacao(cpf, id);
            this.getFacade().verificarSeAlunoJaRespondeuAvaliacao(
                    avaliacaoDTO.getTurmaDTO(), avaliacaoDTO.getAlunoDTO());
            model.addAttribute(avaliacaoDTO);
            this.recuperarTopicoComPerguntas(avaliacaoDTO, model);
            model.addAttribute(ApplicationConstant.EXIBIR_BOTAO_PROXIMO, Boolean.TRUE);
            model.addAttribute(ApplicationConstant.EXIBIR_BOTAO_SALVAR, Boolean.FALSE);
        } catch (RuntimeException ex) {//NOSONAR a exceção é direcionada para a camada de visão da aplicação, portanto o uso do 'throw' neste ponto não é utilizado.
            model.addAttribute(MessageConstant.ERROR, ex.getLocalizedMessage());
        }
        return getViewForm();
    }

    /**
     * Método que exibe a tela de agradecimento para o usuário.
     * @return ModelAndView
     */
    @RequestMapping(value = ActionConstant.ACTION_AGRADECIMENTO)
    public String agradecimento(){
        return this.getViewAgradecimento();
    }

    private boolean isVerificarIndiceAtualComTamanhoTotalDaListaDeTopico(AvaliacaoDTO avaliacaoDTO){
        return avaliacaoDTO.isExisteTemplateAvaliacaoAndTemplateTopico() &&
                avaliacaoDTO.isExisteProximoTopico();
    }

    private void buscarProximoTopicoComPerguntas(AvaliacaoDTO avaliacaoDTO, Model model){
        if(avaliacaoDTO.isExisteTemplateAvaliacaoAndTemplateTopico()){
            this.recuperarTopicoComPerguntas(avaliacaoDTO, model);
        }
    }

    private void recuperarTopicoComPerguntas(AvaliacaoDTO avaliacaoDTO, Model model){
        List<PerguntaAssociadaWrapper> perguntaAssociadaWrapperList = new ArrayList<>();
        avaliacaoDTO.showNextTopicoComPergunta(perguntaAssociadaWrapperList, avaliacaoDTO.getIndiceTopico());
        model.addAttribute(perguntaAssociadaWrapperList);
    }

    /**
     * Método que redireciona para a tela de cadastrar/alterar.
     * @return String
     */
    private String getViewForm(){
        return getPathView() + ViewConstant.VIEW_FORM;
    }

    /**
     * Método que faz o redirecionamento para a ação de exbir a tela de agradecimento.
     * @return String
     */
    private String getRedirectViewAgradecimento(){
        return ActionConstant.REDIRECT + getPathView() + ActionConstant.ACTION_AGRADECIMENTO;
    }

    /**
     * Método que redireciona para a tela de agradecimento.
     * @return String
     */
    private String getViewAgradecimento(){
        return getPathView() + ViewConstant.VIEW_AGRADECIMENTO;
    }

    private AvaliacaoFacade getFacade() {
        return this.avaliacaoFacade;
    }

    private String getPathView() {
        return PathConstant.PATH_RESPOSTA_AVALIACAO;
    }

}
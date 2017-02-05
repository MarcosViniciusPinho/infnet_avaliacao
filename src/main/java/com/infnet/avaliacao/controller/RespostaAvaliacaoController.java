package com.infnet.avaliacao.controller;

import com.infnet.avaliacao.business.facade.IAvaliacaoFacade;
import com.infnet.avaliacao.controller.util.ActionConstant;
import com.infnet.avaliacao.controller.util.MessageConstant;
import com.infnet.avaliacao.controller.util.PathConstant;
import com.infnet.avaliacao.dto.impl.AvaliacaoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 * Classe responsável pela captura das informações na view.
 */
@Controller
@RequestMapping(value = PathConstant.PATH_RESPOSTA_AVALIACAO)
public class RespostaAvaliacaoController {

    private static final String VIEW_FORM = "/form";
    private static final String VIEW_AGRADECIMENTO = "/agradecimento";

    @Resource
    private IAvaliacaoFacade avaliacaoFacade;

    /**
     * Método que salva/altera uma entidade.
     * @param avaliacaoDTO avaliacaoDTO
     * @param redirectAttributes redirectAttributes
     * @return String
     */
    @RequestMapping(value = ActionConstant.ACTION_SAVE, method = RequestMethod.POST)
    public String save(AvaliacaoDTO avaliacaoDTO, RedirectAttributes redirectAttributes, Model model){
        try{
            this.getFacade().save(avaliacaoDTO);
            redirectAttributes.addFlashAttribute(MessageConstant.SUCESS, MessageConstant.MENSAGEM_SUCESSO);
            return getViewAgradecimento();
        } catch (RuntimeException ex) {
            model.addAttribute(MessageConstant.ERROR, ex.getLocalizedMessage());
            this.onLoadView(model);
            return getViewForm();
        }
    }

    /**
     * Método que redireciona o usuário para a tela de responder avaliacao.
     * @param model model
     * @return ModelAndView
     */
    @RequestMapping(value = ActionConstant.ACTION_LINK_RESPONDER_AVALIACAO)
    public String prepareCreate(@PathVariable Long cpf, @PathVariable Long id, Model model){
//        this.onLoadView(model);
        boolean existeCpfInformado = this.getFacade().isExisteCpf(cpf);
        if(existeCpfInformado){
            AvaliacaoDTO avaliacaoDTO = this.getFacade().popularAlunoAndTurmaParaAvaliacao(cpf, id);
            model.addAttribute(avaliacaoDTO);
        }
        return getViewForm();
    }

    /**
     * Método que redireciona para a tela de cadastrar/alterar.
     * @return String
     */
    private String getViewForm(){
        return getPathView() + VIEW_FORM;
    }

    /**
     * Método que redireciona para a tela de agradecimento para o usuario.
     * @return String
     */
    private String getViewAgradecimento(){
        return getPathView() + VIEW_AGRADECIMENTO;
    }

    private void onLoadView(Model model){
    }

    private IAvaliacaoFacade getFacade() {
        return this.avaliacaoFacade;
    }

    private String getPathView() {
        return PathConstant.PATH_RESPOSTA_AVALIACAO;
    }

}
package com.infnet.avaliacao.controller;

import com.infnet.avaliacao.business.facade.ITemplateAvaliacaoFacade;
import com.infnet.avaliacao.exception.ExecutionException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 * Classe responsável pela captura das informações na view.
 */
@Controller
@RequestMapping(value = TemplateAvaliacaoController.PATH_TEMPLATE_AVALIACAO)
public class TemplateAvaliacaoController{

    protected static final String PATH_TEMPLATE_AVALIACAO = "/avaliacao";
    private static final String ACTION_LIST = "/list";
    private static final String VIEW_LIST = "/list";
    private static final String LISTAR_TEMPLATE_AVALIACAO = "listarTemplateAvaliacao";
    private static final String ACTION_DELETE = "/delete/{id}";
    private static final String SUCESS="sucess";
    private static final String MENSAGEM_SUCESSO = "mensagem.sucesso";
    private static final String REDIRECT_LIST = "redirect:";

    @Resource
    private ITemplateAvaliacaoFacade templateAvaliacaoFacade;

    /**
     * Método que faz a listagem dos registros na tela.
     * @return ModelAndView
     */
    @RequestMapping(value = ACTION_LIST)
    public ModelAndView list(){
        try {
            ModelAndView mv = new ModelAndView(getViewList());
            mv.addObject(LISTAR_TEMPLATE_AVALIACAO, this.templateAvaliacaoFacade.findAll());
            return mv;
        } catch (RuntimeException ex) {
            throw new ExecutionException(ex);
        }
    }

    /**
     * Método que exclui uma entidade.
     * @param id id
     * @param redirectAttributes redirectAttributes
     * @return String
     */
    @RequestMapping(value = ACTION_DELETE, method = RequestMethod.POST)
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes){
        try {
            this.getFacade().delete(id);
            redirectAttributes.addFlashAttribute(SUCESS, MENSAGEM_SUCESSO);
            return this.getRedirectViewList();
        } catch (RuntimeException ex) {
            throw new ExecutionException(ex);
        }
    }

    /**
     * Pega o contexto do controler que sera usado para view.
     * @return String
     */
    private String getPathView() {
        return PATH_TEMPLATE_AVALIACAO;
    }

    /**
     * Método que redireciona para listagem dos registros
     * @return String
     */
    private String getViewList(){
        return getPathView() + VIEW_LIST;
    }

    private ITemplateAvaliacaoFacade getFacade() {
        return templateAvaliacaoFacade;
    }

    /**
     * Método que redireciona para listagem dos registros com redirect
     * @return String
     */
    private String getRedirectViewList(){
        return REDIRECT_LIST + getPathView() + ACTION_LIST;
    }

}
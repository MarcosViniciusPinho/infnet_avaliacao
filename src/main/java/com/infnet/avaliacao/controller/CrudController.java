package com.infnet.avaliacao.controller;

import com.infnet.avaliacao.business.facade.ICrudFacade;
import com.infnet.avaliacao.controller.util.ActionConstant;
import com.infnet.avaliacao.exception.ExecutionException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Classe responsável por executar as funcionalidades em comum nos módulos da aplicação.
 * @param <V> dto
 */
public abstract class CrudController<V> {

    private static final String VIEW_LIST = "/list";
    private static final String VIEW_FORM = "/form";
    private static final String VIEW_DETAIL = "/detail";
    protected static final String REDIRECT_LIST = "redirect:";

    /**
     * Método que deverá ser sempre implementado por suas subclasses.
     * @return ICrudFacade<T>
     */
    protected abstract ICrudFacade<V> getFacade();

    /**
     * Método que faz a listagem dos registros na tela.
     * @return ModelAndView
     */
    @RequestMapping(value = ActionConstant.ACTION_LIST)
    public ModelAndView list(){
        try {
            return this.onList();
        } catch (RuntimeException ex) {
            throw new ExecutionException(ex);
        }
    }

    /**
     * Método responsável de montar as informacoes no grid da tela e que deve ser implementado nas subclasses.
     * @return ModelAndView
     */
    protected abstract ModelAndView onList();

    /**
     * Pega o contexto do controler que sera usado para view.
     * @return String
     */
    protected abstract String getPathView();

    /**
     * Método que redireciona para a tela de cadastrar/alterar.
     * @return String
     */
    protected String getViewForm(){
        return getPathView() + VIEW_FORM;
    }

    /**
     * Método que redireciona para a tela de detalhar.
     * @return String
     */
    protected String getViewDetail(){
        return getPathView() + VIEW_DETAIL;
    }

    /**
     * Método que redireciona para listagem dos registros
     * @return String
     */
    protected String getViewList(){
        return getPathView() + VIEW_LIST;
    }

    /**
     * Método que redireciona para listagem dos registros com redirect
     * @return String
     */
    protected String getRedirectViewList(){
        return REDIRECT_LIST + getPathView() + ActionConstant.ACTION_LIST;
    }

    /**
     * Método para carregar informações em comum para as telas de Salvar e Alterar.
     * @param model model
     */
    protected abstract void onLoadView(Model model);

}

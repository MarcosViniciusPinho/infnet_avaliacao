package com.infnet.avaliacao.controller;

import com.infnet.avaliacao.business.facade.CrudFacade;
import org.springframework.ui.Model;

/**
 * Classe responsável por executar as funcionalidades em comum nos módulos da aplicação.
 * @param <V> dto
 */
public abstract class InitController<V> {

    private static final String VIEW_LIST = "/list";
    private static final String VIEW_FORM = "/form";
    private static final String VIEW_DETAIL = "/detail";
    protected static final String REDIRECT_LIST = "redirect:";

    /**
     * Método que deverá ser sempre implementado por suas subclasses.
     * @return CrudFacade<T>
     */
    protected abstract CrudFacade<V> getFacade();

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
     * Método para carregar informações em comum para as telas de Salvar e Alterar.
     * @param model model
     */
    protected abstract void onLoadView(Model model);

}

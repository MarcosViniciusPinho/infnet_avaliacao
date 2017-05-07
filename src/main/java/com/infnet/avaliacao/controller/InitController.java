package com.infnet.avaliacao.controller;

import com.infnet.avaliacao.business.facade.CrudFacade;
import com.infnet.avaliacao.controller.util.ViewConstant;
import org.springframework.ui.Model;

/**
 * Classe responsável por executar as funcionalidades em comum nos módulos da aplicação.
 * @param <V> dto
 */
public abstract class InitController<V> {

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
        return getPathView() + ViewConstant.VIEW_FORM;
    }

    /**
     * Método que redireciona para a tela de detalhar.
     * @return String
     */
    protected String getViewDetail(){
        return getPathView() + ViewConstant.VIEW_DETAIL;
    }

    /**
     * Método que redireciona para listagem dos registros
     * @return String
     */
    protected String getViewList(){
        return getPathView() + ViewConstant.VIEW_LIST;
    }

    /**
     * Método para carregar informações em comum para as telas de Salvar e Alterar.
     * @param model model
     */
    protected abstract void onLoadView(Model model);

}

package com.infnet.avaliacao.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Classe responsável por executar o comportamento padrão do módulo de templates da aplicação.
 * @param <V> dto
 */
public abstract class TemplateController<V> extends CrudController<V>{

    private static final String ACTION_ERROR = "/error/{id}";

    /**
     * Houve necessidade de reimplementação do método pois o comportamento de montar avaliação é diferente do padrão adotado.
     * @param entity entity
     * @param redirectAttributes redirectAttributes
     * @param model model
     * @return String
     */
    @RequestMapping(value = ACTION_SAVE, method = RequestMethod.POST)
    public String save(V entity, RedirectAttributes redirectAttributes, Model model){
        try{
            this.onForm(entity, model, redirectAttributes);
            this.getFacade().save(entity);
            redirectAttributes.addFlashAttribute(SUCESS, MENSAGEM_SUCESSO);
            return this.getRedirectViewEdit();
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute(ERROR, ex.getLocalizedMessage());
            this.onLoadView(model);
            return getRedirectViewError();
        }
    }

    /**
     * Método para popular a lista de templates topicos selecionados na tela de form.
     * @param entity entity
     * @param model model
     * @param redirectAttributes redirectAttributes
     */
    protected abstract void onForm(V entity, Model model, RedirectAttributes redirectAttributes);

    /**
     * Método que redireciona para a tela de form com os check box's marcados.
     * @return String
     */
    private String getRedirectViewEdit(){
        return REDIRECT_LIST + getPathView() + ACTION_EDIT;
    }

    /**
     * Método que redireciona para a tela de form com os check box's desmarcados.
     * @return String
     */
    private String getRedirectViewError(){
        return REDIRECT_LIST + getPathView() + ACTION_ERROR;
    }

    /**
     * Método que redireciona o usuário para a tela de alterar.
     * @param id id
     * @param model model
     * @return String
     */
    @RequestMapping(value = ACTION_EDIT)
    public String prepareUpdate(@PathVariable Long id, Model model){
        this.onEdit(id, model);
        return this.getViewForm();
    }

    /**
     * Método que redireciona o usuário para a tela de form quando houver exceção.
     * @param id id
     * @param model model
     * @return String
     */
    @RequestMapping(value = ACTION_ERROR)
    public String prepareError(@PathVariable Long id, Model model){
        this.onErrorOrDetail(id, model);
        return this.getViewForm();
    }

    /**
     * Método que redireciona o usuário para a tela de detalhar.
     * @param id id
     * @param model model
     * @return String
     */
    @RequestMapping(value = ACTION_DETAIL)
    public String prepareDetail(@PathVariable Long id, Model model){
        this.onErrorOrDetail(id, model);
        return getViewDetail();
    }

    /**
     * Método usado para quando houver uma exceção na tela de form e também reaproveitado para ser usado no momento de detalhar.
     * @param id id
     * @param model model
     */
    protected abstract void onErrorOrDetail(Long id, Model model);

    protected abstract void onEdit(Long id, Model model);

}

package com.infnet.avaliacao.controller;

import com.infnet.avaliacao.business.facade.ICrudFacade;
import com.infnet.avaliacao.exception.ExecutionException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Classe responsável por executar o CRUD da aplicação, assim como todos os métodos comuns que todos utilizem em todoo o sistema.
 * @param <V> dto
 */
public abstract class CrudController<V> {

    private static final String ACTION_LIST = "/list";
    private static final String ACTION_CREATE = "/create";
    private static final String ACTION_EDIT = "/edit/{id}";
    private static final String ACTION_SAVE = "/save";
    private static final String MENSAGEM_SUCESSO = "Operação realizada com sucesso.";
    private static final String ACTION_DETAIL = "/detail/{id}";
    private static final String ACTION_DELETE = "/delete/{id}";
    private static final String SUCESS="sucess";
    private static final String ERROR="error";

    private static final String VIEW_LIST = "/list";
    private static final String VIEW_FORM = "/form";
    private static final String VIEW_DETAIL = "/detail";
    private static final String REDIRECT_LIST = "redirect:";

    /**
     * Método que deverá ser sempre implementado por suas subclasses.
     * @return ICrudFacade<T>
     */
    protected abstract ICrudFacade<V> getFacade();

    /**
     * Método que salva/altera uma entidade.
     * @param entity entity
     * @param redirectAttributes redirectAttributes
     * @return String
     */
    @RequestMapping(value = ACTION_SAVE, method = RequestMethod.POST)
    public String save(V entity, RedirectAttributes redirectAttributes, Model model){
        try{
            this.getFacade().save(entity);
            redirectAttributes.addFlashAttribute(SUCESS, MENSAGEM_SUCESSO);
            return this.getRedirectViewList();
        } catch (RuntimeException ex) {
            model.addAttribute(ERROR, ex.getLocalizedMessage());
            this.onLoadView(model);
            return this.getViewForm();
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
     * Método que faz a listagem dos registros na tela.
     * @return ModelAndView
     */
    @RequestMapping(value = ACTION_LIST)
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
     * Método que redireciona o usuário para a tela de cadastrar.
     * @param model model
     * @return ModelAndView
     */
    @RequestMapping(value = ACTION_CREATE)
    public String prepareCreate(Model model){
        return this.onPrepareCreate(model);
    }

    /**
     * Método responsável de montar as informacoes que irao aparecer na tela de cadastrar e que deve ser implementado nas subclasses.
     * @param model model
     * @return String
     */
    protected abstract String onPrepareCreate(Model model);

    /**
     * Método que redireciona o usuário para a tela de alterar.
     * @param id id
     * @param model model
     * @return String
     */
    @RequestMapping(value = ACTION_EDIT)
    public String prepareUpdate(@PathVariable Long id, Model model){
        return this.onPrepareUpdateOrDetail(this.getViewForm(), id, model);
    }

    /**
     * Método que redireciona o usuário para a tela de detalhar.
     * @param id id
     * @param model model
     * @return String
     */
    @RequestMapping(value = ACTION_DETAIL)
    public String prepareDetail(@PathVariable Long id, Model model){
        return this.onPrepareUpdateOrDetail(this.getViewDetail(), id, model);
    }

    /**
     * Método responsável de montar as informacoes que irao aparecer na tela de alterar/detalhar e que deve ser implementado nas subclasses.
     * @param view view
     * @param id id
     * @param model model
     * @return String
     */
    protected abstract String onPrepareUpdateOrDetail(String view, Long id, Model model);

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
    private String getViewDetail(){
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
    private String getRedirectViewList(){
        return REDIRECT_LIST + getPathView() + ACTION_LIST;
    }

    /**
     * Método para carregar informações em comum para as telas de Salvar e Alterar.
     * @param model model
     */
    protected void onLoadView(Model model){ }

}

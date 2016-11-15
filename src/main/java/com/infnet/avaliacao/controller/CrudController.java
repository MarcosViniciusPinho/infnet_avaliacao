package com.infnet.avaliacao.controller;

import com.infnet.avaliacao.business.facade.ICrudFacade;
import com.infnet.avaliacao.exception.ExecutionException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Classe responsável por executar o CRUD da aplicação, assim como todos os métodos comuns que todos utilizem em todoo o sistema.
 * @param <V> dto
 * @param <T> entidade
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

    private static final String VIEW_LIST = "/List";
    private static final String VIEW_FORM = "/Form";
    private static final String VIEW_DETAIL = "/Detail";
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
    public String save(V entity, RedirectAttributes redirectAttributes){
        try{
            this.getFacade().save(entity);
            redirectAttributes.addFlashAttribute(SUCESS, MENSAGEM_SUCESSO);
            return this.getRedirectViewList();
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
     * @return ModelAndView
     */
    @RequestMapping(value = ACTION_CREATE)
    public ModelAndView prepareCreate(){
        return this.onPrepareCreate();
    }

    /**
     * Método responsável de montar as informacoes que irao aparecer na tela de cadastrar e que deve ser implementado nas subclasses.
     * @return ModelAndView
     */
    protected abstract ModelAndView onPrepareCreate();

    /**
     * Método que redireciona o usuário para a tela de alterar.
     * @param id id
     * @return ModelAndView
     */
    @RequestMapping(value = ACTION_EDIT)
    public ModelAndView prepareUpdate(@PathVariable Long id){
        return this.onPrepareUpdateOrDetail(this.getViewForm(), id);
    }

    /**
     * Método que redireciona o usuário para a tela de detalhar.
     * @param id id
     * @return ModelAndView
     */
    @RequestMapping(value = ACTION_DETAIL)
    public ModelAndView prepareDetail(@PathVariable Long id){
        return this.onPrepareUpdateOrDetail(this.getViewDetail(), id);
    }

    /**
     * Método responsável de montar as informacoes que irao aparecer na tela de alterar/detalhar e que deve ser implementado nas subclasses.
     * @param view view
     * @param id id
     * @return ModelAndView
     */
    protected abstract ModelAndView onPrepareUpdateOrDetail(String view, Long id);

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
     * Método que redireciona para listagem dos registros(Porto, Armador e Rebocador)
     * @return String
     */
    protected String getViewList(){
        return getPathView() + VIEW_LIST;
    }

    /**
     * Método que redireciona para listagem dos registros(Porto, Armador e Rebocador)
     * @return String
     */
    private String getRedirectViewList(){
        return REDIRECT_LIST + getPathView() + ACTION_LIST;
    }

}

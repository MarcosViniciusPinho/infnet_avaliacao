package com.infnet.avaliacao.controller;

import com.infnet.avaliacao.controller.util.ActionConstant;
import com.infnet.avaliacao.controller.util.MessageConstant;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Classe responsável por executar o comportamento padrão do módulo cadastro da aplicação.
 * @param <V> dto
 */
public abstract class CadastroController<V> extends CrudController<V>{

    /**
     * Método que salva/altera uma entidade.
     * @param entity entity
     * @param redirectAttributes redirectAttributes
     * @return String
     */
    @RequestMapping(value = ActionConstant.ACTION_SAVE, method = RequestMethod.POST)
    public String save(V entity, RedirectAttributes redirectAttributes, Model model){
        try{
            this.getFacade().save(entity);
            redirectAttributes.addFlashAttribute(MessageConstant.SUCESS, MessageConstant.MENSAGEM_SUCESSO);
            return getRedirectViewList();
        } catch (RuntimeException ex) {
            model.addAttribute(MessageConstant.ERROR, ex.getLocalizedMessage());
            this.onLoadView(model);
            return getViewForm();
        }
    }

    /**
     * Método que redireciona o usuário para a tela de cadastrar.
     * @param model model
     * @return ModelAndView
     */
    @RequestMapping(value = ActionConstant.ACTION_CREATE)
    public String prepareCreate(Model model){
        return this.onPrepareCreate(model);
    }

    /**
     * Método que redireciona o usuário para a tela de alterar.
     * @param id id
     * @param model model
     * @return String
     */
    @RequestMapping(value = ActionConstant.ACTION_EDIT)
    public String prepareUpdate(@PathVariable Long id, Model model){
        return this.onPrepareUpdateOrDetail(getViewForm(), id, model);
    }

    /**
     * Método que redireciona o usuário para a tela de detalhar.
     * @param id id
     * @param model model
     * @return String
     */
    @RequestMapping(value = ActionConstant.ACTION_DETAIL)
    public String prepareDetail(@PathVariable Long id, Model model){
        return this.onPrepareUpdateOrDetail(getViewDetail(), id, model);
    }

    /**
     * Método responsável de montar as informacoes que irao aparecer na tela de cadastrar e que deve ser implementado nas subclasses.
     * @param model model
     * @return String
     */
    protected abstract String onPrepareCreate(Model model);

    /**
     * Método responsável de montar as informacoes que irao aparecer na tela de alterar/detalhar e que deve ser implementado nas subclasses.
     * @param view view
     * @param id id
     * @param model model
     * @return String
     */
    protected abstract String onPrepareUpdateOrDetail(String view, Long id, Model model);

}

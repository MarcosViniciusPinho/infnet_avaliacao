package com.infnet.avaliacao.controller;

import com.infnet.avaliacao.controller.util.ActionConstant;
import com.infnet.avaliacao.controller.util.MessageConstant;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Classe responsável por executar o comportamento padrão do módulo de templates da aplicação.
 * @param <V> dto
 */
public abstract class TemplateController<V> extends InitController<V> {

    /**
     * Houve necessidade de reimplementação do método pois o comportamento de montar avaliação é diferente do padrão adotado.
     * @param entity entity
     * @param redirectAttributes redirectAttributes
     * @param model model
     * @return String
     */
    @RequestMapping(value = ActionConstant.ACTION_SAVE, method = RequestMethod.POST)
    public String save(V entity, RedirectAttributes redirectAttributes, Model model){
        try{
            this.onForm(entity, model, redirectAttributes);
            this.getFacade().save(entity);
            redirectAttributes.addFlashAttribute(MessageConstant.SUCESS, MessageConstant.MENSAGEM_SUCESSO);
            return this.getRedirectViewEdit();
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute(MessageConstant.ERROR, ex.getLocalizedMessage());
            this.onLoadView(model);
            return this.getRedirectViewError();
        }
    }

    /**
     * Método para popular o objeto com as informações necessárias a serem preenchidos.
     * @param entity entity
     * @param model model
     * @param redirectAttributes redirectAttributes
     */
    protected abstract void onForm(V entity, Model model, RedirectAttributes redirectAttributes);

    /**
     * Método que redireciona para a tela de form com os check box's marcados.
     * @return String
     */
    protected abstract String getRedirectViewEdit();

    /**
     * Método que redireciona para a tela de form com os check box's desmarcados.
     * @return String
     */
    protected abstract String getRedirectViewError();

}

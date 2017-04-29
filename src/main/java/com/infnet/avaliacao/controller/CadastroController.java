package com.infnet.avaliacao.controller;

import com.infnet.avaliacao.controller.util.ActionConstant;
import com.infnet.avaliacao.controller.util.MessageConstant;
import com.infnet.avaliacao.exception.BusinessException;
import com.infnet.avaliacao.exception.ExecutionException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * Classe responsável por executar o comportamento padrão do módulo cadastro da aplicação.
 * @param <V> dto
 */
public abstract class CadastroController<V> extends InitController<V> {

    /**
     * Método que faz a listagem dos registros na tela.
     * @return ModelAndView
     */
    @RequestMapping(value = ActionConstant.ACTION_LIST)
    public ModelAndView list(@PageableDefault Pageable pageable){
        try {
            return this.onList(pageable);
        } catch (RuntimeException ex) {
            throw new ExecutionException(ex);
        }
    }

    /**
     * Método responsável de montar as informacoes no grid da tela e que deve ser implementado nas subclasses.
     * @param pageable pageable
     * @return ModelAndView
     */
    protected abstract ModelAndView onList(Pageable pageable);

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
        } catch (ConstraintViolationException ex) {//Validação de campos obrigatórios
            this.tratarExcecao(model, ex.getConstraintViolations(), MessageConstant.SET_EXCEPTION_REQUIRED);
        } catch (BusinessException ex){//Validação de negócio
            this.tratarExcecao(model, ex.getMultipleMessages(), MessageConstant.SET_EXCEPTION_BUSINESS);
        }
        return getViewForm();
    }

    private void tratarExcecao(Model model, Set multipleMessages, String keyError){
        model.addAttribute(keyError, multipleMessages);
        this.onLoadView(model);
    }

    /**
     * Método que exclui uma entidade.
     * @param id id
     * @param redirectAttributes redirectAttributes
     * @return String
     */
    @RequestMapping(value = ActionConstant.ACTION_DELETE, method = RequestMethod.POST)
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes){
        try {
            this.onDelete(id);
            redirectAttributes.addFlashAttribute(MessageConstant.SUCESS, MessageConstant.MENSAGEM_SUCESSO);
            return this.getRedirectViewList();
        } catch (RuntimeException ex) {
            throw new ExecutionException(ex);
        }
    }

    /**
     * Método precisou ser criado pois o método de delete deixou de ser genérico e os módulos que precisarem da funcionalidade excluir
     * deverão implementar este método e chamar o delete atraves de seus respectivos facades.
     * @param id id
     */
    protected abstract void onDelete(Long id);

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

    /**
     * Método que redireciona para listagem dos registros com redirect
     * @return String
     */
    protected String getRedirectViewList(){
        return ActionConstant.REDIRECT + getPathView() + ActionConstant.ACTION_LIST;
    }

}

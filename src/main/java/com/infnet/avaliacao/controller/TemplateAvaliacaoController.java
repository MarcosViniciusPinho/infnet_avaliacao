package com.infnet.avaliacao.controller;

import com.infnet.avaliacao.business.facade.ITemplateAvaliacaoFacade;
import com.infnet.avaliacao.dto.impl.TemplateAvaliacaoDTO;
import com.infnet.avaliacao.exception.ExecutionException;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

/**
 * Classe responsável pela captura das informações na view.
 */
@Controller
@RequestMapping(value = TemplateAvaliacaoController.PATH_TEMPLATE_AVALIACAO)
public class TemplateAvaliacaoController{

    protected static final String PATH_TEMPLATE_AVALIACAO = "/template/avaliacao";
    private static final String ACTION_LIST = "/list";
    private static final String VIEW_LIST = "/list";
    private static final String LISTAR_TEMPLATE_AVALIACAO = "listarTemplateAvaliacao";
    private static final String ACTION_DELETE = "/delete/{id}";
    private static final String SUCESS="sucess";
    private static final String MENSAGEM_SUCESSO = "mensagem.sucesso";
    private static final String REDIRECT_LIST = "redirect:";
    private static final String ACTION_DETAIL = "/detail/{id}";
    private static final String VIEW_DETAIL = "/detail";
    private static final String ACTION_EDIT = "/edit/{id}";
    private static final String VIEW_FORM = "/form";
    private static final String ACTION_SAVE = "/save";
    private static final String ERROR="error";

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
     * Método que redireciona o usuário para a tela de alterar.
     * @param id id
     * @param model model
     * @return String
     */
    @RequestMapping(value = ACTION_EDIT)
    public String prepareUpdate(@PathVariable Long id, Model model){
        return this.onPrepareUpdateOrDetail(this.getViewForm(), id, model);
    }

    private String onPrepareUpdateOrDetail(String view, Long id, Model model){
        ParameterExceptionUtil.validateObjectNull(view);
        ParameterExceptionUtil.validateObjectNull(id);
        model.addAttribute(this.templateAvaliacaoFacade.findById(id));
        return view;
    }

    /**
     * Método que salva/altera uma entidade.
     * @param entity entity
     * @param redirectAttributes redirectAttributes
     * @return String
     */
    @RequestMapping(value = ACTION_SAVE, method = RequestMethod.POST)
    public String save(TemplateAvaliacaoDTO entity, RedirectAttributes redirectAttributes, Model model){
        try{
            this.onForm(entity);
            this.getFacade().save(entity);
            redirectAttributes.addFlashAttribute(SUCESS, MENSAGEM_SUCESSO);
            return this.getRedirectViewList();
        } catch (RuntimeException ex) {
            model.addAttribute(ERROR, ex.getLocalizedMessage());
            this.onLoadView(model);
            return this.getViewForm();
        }
    }

    private void onForm(TemplateAvaliacaoDTO entity){
        List<Long> idsTopicosSelecionados = entity.getIdsTemplateTopicoSelecionados();


    }

    /**
     * Método para carregar informações em comum para as telas de Salvar e Alterar.
     * @param model model
     */
    protected void onLoadView(Model model){ }

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

    /**
     * Método que redireciona para a tela de detalhar.
     * @return String
     */
    private String getViewDetail(){
        return getPathView() + VIEW_DETAIL;
    }

    /**
     * Método que redireciona para a tela de cadastrar/alterar.
     * @return String
     */
    protected String getViewForm(){
        return getPathView() + VIEW_FORM;
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
package com.infnet.avaliacao.controller;

import com.infnet.avaliacao.business.facade.TemplateAvaliacaoFacade;
import com.infnet.avaliacao.business.facade.TemplateTopicoFacade;
import com.infnet.avaliacao.controller.util.ActionConstant;
import com.infnet.avaliacao.controller.util.ApplicationConstant;
import com.infnet.avaliacao.controller.util.PathConstant;
import com.infnet.avaliacao.controller.wrapper.PerguntaAssociadaWrapper;
import com.infnet.avaliacao.dto.impl.TemplateAvaliacaoDTO;
import com.infnet.avaliacao.exception.ExecutionException;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pela captura das informações na view.
 */
@Controller
@RequestMapping(value = PathConstant.PATH_TEMPLATE_AVALIACAO)
public class TemplateAvaliacaoController extends TemplateController<TemplateAvaliacaoDTO>{

    @Resource
    private TemplateAvaliacaoFacade templateAvaliacaoFacade;

    @Resource
    private TemplateTopicoFacade templateTopicoFacade;

    /**
     * Método que faz a listagem dos registros na tela.
     * @return ModelAndView
     */
    @RequestMapping(value = ActionConstant.ACTION_LIST)
    public ModelAndView list(@PageableDefault Pageable pageable){
        ParameterExceptionUtil.validateObjectNull(pageable);
        try {
            ModelAndView mv = new ModelAndView(getViewList());
            mv.addObject(ApplicationConstant.LISTAR_TEMPLATE_AVALIACAO, this.templateAvaliacaoFacade.findAllPaginated(pageable));
            return mv;
        } catch (RuntimeException ex) {
            throw new ExecutionException(ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onForm(TemplateAvaliacaoDTO entity, Model model, RedirectAttributes redirectAttributes) {
        ParameterExceptionUtil.validateObjectNull(entity);
        ParameterExceptionUtil.validateObjectNull(model);
        ParameterExceptionUtil.validateObjectNull(redirectAttributes);
        List<Long> idsTopicosSelecionados = entity.getIdsTemplateTopicoSelecionados();
        entity.setTemplateTopicoDTOList(
                this.templateTopicoFacade.getListaTemplatesTopicosPorId(idsTopicosSelecionados));
        redirectAttributes.addAttribute("id", entity.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onLoadViewPaginated(Model model, Pageable pageable){
        model.addAttribute(ApplicationConstant.LISTAR_TEMPLATE_TOPICO, templateTopicoFacade.findAllPaginated(pageable));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onLoadView(Model model){
        model.addAttribute(ApplicationConstant.LISTAR_TEMPLATE_TOPICO, templateTopicoFacade.findAll());
    }

    private void onEdit(Long id, Model model, Pageable pageable){
        this.onLoadViewPaginated(model, pageable);
        TemplateAvaliacaoDTO templateAvaliacaoDTO = this.templateAvaliacaoFacade.findById(id);
        model.addAttribute(templateAvaliacaoDTO.carregarTopicosCadastradosParaFicarSelecionados());
    }

    /**
     * Método que redireciona o usuário para a tela de alterar.
     * @param id id
     * @param model model
     * @return String
     */
    @RequestMapping(value = ActionConstant.ACTION_EDIT)
    public String prepareUpdate(@PathVariable Long id, Model model, @PageableDefault Pageable pageable){
        this.onEdit(id, model, pageable);
        return getViewForm();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRedirectViewEdit(){
        return ActionConstant.REDIRECT + getPathView() + ActionConstant.ACTION_EDIT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRedirectViewError(){
        return ActionConstant.REDIRECT + getPathView() + ActionConstant.ACTION_ERROR;
    }

    /**
     * Método que redireciona o usuário para a tela de form quando houver exceção.
     * @param id id
     * @param model model
     * @return String
     */
    @RequestMapping(value = ActionConstant.ACTION_ERROR)
    public String prepareError(@PathVariable Long id, Model model, @PageableDefault Pageable pageable){
        this.onError(id, model, pageable);
        return getViewForm();
    }

    /**
     * Método que redireciona o usuário para a tela de detalhar.
     * @param id id
     * @param model model
     * @return String
     */
    @RequestMapping(value = ActionConstant.ACTION_DETAIL)
    public String prepareDetail(@PathVariable Long id, Model model){
        this.onDetail(id, model);
        return getViewDetail();
    }

    /**
     * Método usado para quando houver uma exceção na tela de form.
     * @param id id
     * @param model model
     */
    private void onError(Long id, Model model, Pageable pageable){
        model.addAttribute(this.templateAvaliacaoFacade.findById(id));
        this.onLoadViewPaginated(model, pageable);
    }

    /**
     * Método usado o momento de detalhar a avaliação.
     * @param id id
     * @param model model
     */
    private void onDetail(Long id, Model model){
        TemplateAvaliacaoDTO templateAvaliacaoDTO = this.templateAvaliacaoFacade.findById(id);
        List<PerguntaAssociadaWrapper> perguntaAssociadaWrapperList = new ArrayList<>();
        templateAvaliacaoDTO.detail(perguntaAssociadaWrapperList);
        model.addAttribute(templateAvaliacaoDTO);
        model.addAttribute(perguntaAssociadaWrapperList);
        this.onLoadView(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPathView() {
        return PathConstant.PATH_TEMPLATE_AVALIACAO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected TemplateAvaliacaoFacade getFacade() {
        return templateAvaliacaoFacade;
    }

}
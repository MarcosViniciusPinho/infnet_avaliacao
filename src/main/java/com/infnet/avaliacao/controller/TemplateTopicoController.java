package com.infnet.avaliacao.controller;

import com.infnet.avaliacao.business.facade.TemplateAvaliacaoFacade;
import com.infnet.avaliacao.business.facade.TemplatePerguntaFacade;
import com.infnet.avaliacao.business.facade.TemplateTopicoFacade;
import com.infnet.avaliacao.controller.util.ActionConstant;
import com.infnet.avaliacao.controller.util.ApplicationConstant;
import com.infnet.avaliacao.controller.util.PathConstant;
import com.infnet.avaliacao.dto.impl.TemplateAvaliacaoDTO;
import com.infnet.avaliacao.dto.impl.TemplatePerguntaDTO;
import com.infnet.avaliacao.dto.impl.TemplateTopicoDTO;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

/**
 * Classe responsável pela captura das informações na view.
 */
@Controller
@RequestMapping(value = PathConstant.PATH_TEMPLATE_TOPICO)
public class TemplateTopicoController extends TemplateController<TemplateTopicoDTO>{

    @Resource
    private TemplateTopicoFacade templateTopicoFacade;

    @Resource
    private TemplatePerguntaFacade templatePerguntaFacade;

    @Resource
    private TemplateAvaliacaoFacade templateAvaliacaoFacade;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onForm(TemplateTopicoDTO entity, Model model, RedirectAttributes redirectAttributes) {
        ParameterExceptionUtil.validateObjectNull(entity);
        ParameterExceptionUtil.validateObjectNull(model);
        ParameterExceptionUtil.validateObjectNull(redirectAttributes);
        List<Long> idsPerguntasSelecionados = entity.getIdsTemplatePerguntaSelecionados();
        List<TemplatePerguntaDTO> templatePerguntaDTOList = this.templatePerguntaFacade.getListaTemplatesPerguntasPorId(idsPerguntasSelecionados);
        TemplateAvaliacaoDTO templateAvaliacaoDTO = this.templateAvaliacaoFacade.findById(entity.getIdAvaliacao());

        entity.setTemplateAvaliacaoTopicoPerguntaDTOList(
                this.templatePerguntaFacade.getListaPerguntasAssociadasAoTopicoPorAvaliacao(
                        templatePerguntaDTOList, entity, templateAvaliacaoDTO));

        redirectAttributes.addAttribute("id", entity.getId());
        redirectAttributes.addAttribute("idAvaliacao", entity.getIdAvaliacao());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onLoadView(Model model){
        ParameterExceptionUtil.validateObjectNull(model);
        model.addAttribute(ApplicationConstant.LISTAR_TEMPLATE_PERGUNTA, templatePerguntaFacade.findAll());
    }

    private void onEdit(Long id, Long idAvaliacao, Model model, Pageable pageable){
        TemplateTopicoDTO templateTopicoDTO = this.templateTopicoFacade.findById(id);
        templateTopicoDTO.setIdAvaliacao(idAvaliacao);
        TemplateAvaliacaoDTO templateAvaliacaoDTO = this.templateAvaliacaoFacade.findById(idAvaliacao);
        model.addAttribute(templateAvaliacaoDTO);
        model.addAttribute(templateTopicoDTO);
        model.addAttribute(ApplicationConstant.LISTAR_TEMPLATE_PERGUNTA,
                this.templatePerguntaFacade.findAllComCheckedPerguntasMarcadas(
                        templateTopicoDTO, templateAvaliacaoDTO, pageable));
    }

    /**
     * Método que redireciona o usuário para a tela de alterar.
     * @param id id
     * @param idAvaliacao idAvaliacao
     * @param model model
     * @return String
     */
    @RequestMapping(value = ActionConstant.ACTION_EDIT_CUSTOM)
    public String prepareUpdate(@PathVariable Long id, @PathVariable Long idAvaliacao, Model model, @PageableDefault Pageable pageable){
        ParameterExceptionUtil.validateObjectNull(id);
        ParameterExceptionUtil.validateObjectNull(idAvaliacao);
        ParameterExceptionUtil.validateObjectNull(model);
        ParameterExceptionUtil.validateObjectNull(pageable);
        this.onEdit(id, idAvaliacao, model, pageable);
        return getViewForm();
    }

    /**
     * Método que redireciona o usuário para a tela de form quando houver exceção.
     * @param id id
     * @param idAvaliacao idAvaliacao
     * @param model model
     * @return String
     */
    @RequestMapping(value = ActionConstant.ACTION_ERROR_CUSTOM)
    public String prepareError(@PathVariable Long id, @PathVariable Long idAvaliacao, Model model, @PageableDefault Pageable pageable){
        ParameterExceptionUtil.validateObjectNull(id);
        ParameterExceptionUtil.validateObjectNull(idAvaliacao);
        ParameterExceptionUtil.validateObjectNull(model);
        ParameterExceptionUtil.validateObjectNull(pageable);
        this.onError(id, idAvaliacao, model, pageable);
        return getViewForm();
    }

    /**
     * Método usado para quando houver uma exceção na tela de form.
     * @param id id
     * @param idAvaliacao idAvaliacao
     * @param model model
     */
    private void onError(Long id, Long idAvaliacao, Model model, Pageable pageable){
        TemplateTopicoDTO templateTopicoDTO = this.templateTopicoFacade.findById(id);
        templateTopicoDTO.setIdAvaliacao(idAvaliacao);
        model.addAttribute(templateTopicoDTO);
        model.addAttribute(this.templateAvaliacaoFacade.findById(idAvaliacao));
        this.onLoadViewPaginated(model, pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRedirectViewEdit(){
        return ActionConstant.REDIRECT + getPathView() + ActionConstant.ACTION_EDIT_CUSTOM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRedirectViewError(){
        return ActionConstant.REDIRECT + getPathView() + ActionConstant.ACTION_ERROR_CUSTOM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onLoadViewPaginated(Model model, Pageable pageable) {
        model.addAttribute(ApplicationConstant.LISTAR_TEMPLATE_PERGUNTA, templatePerguntaFacade.findAllPaginated(pageable));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPathView() {
        return PathConstant.PATH_TEMPLATE_TOPICO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected TemplateTopicoFacade getFacade() {
        return templateTopicoFacade;
    }

}
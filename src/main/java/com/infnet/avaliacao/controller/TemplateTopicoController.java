package com.infnet.avaliacao.controller;

import com.infnet.avaliacao.business.facade.ITemplateAvaliacaoFacade;
import com.infnet.avaliacao.business.facade.ITemplatePerguntaFacade;
import com.infnet.avaliacao.business.facade.ITemplateTopicoFacade;
import com.infnet.avaliacao.controller.util.ActionConstant;
import com.infnet.avaliacao.controller.util.PathConstant;
import com.infnet.avaliacao.dto.impl.TemplateAvaliacaoDTO;
import com.infnet.avaliacao.dto.impl.TemplatePerguntaDTO;
import com.infnet.avaliacao.dto.impl.TemplateTopicoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

/**
 * Classe responsável pela captura das informações na view.
 */
@Controller
@RequestMapping(value = PathConstant.PATH_TEMPLATE_TOPICO)
public class TemplateTopicoController extends TemplateController<TemplateTopicoDTO>{

    private static final String LISTAR_TEMPLATE_PERGUNTA = "listarTemplatePergunta";

    @Resource
    private ITemplateTopicoFacade templateTopicoFacade;

    @Resource
    private ITemplatePerguntaFacade templatePerguntaFacade;

    @Resource
    private ITemplateAvaliacaoFacade templateAvaliacaoFacade;

    /**
     * {@inheritDoc}
     */
    @Override
    protected ModelAndView onList(){
        ModelAndView mv = new ModelAndView(getViewList());
        mv.addObject(LISTAR_TEMPLATE_PERGUNTA, this.templatePerguntaFacade.findAll());
        return mv;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onForm(TemplateTopicoDTO entity, Model model, RedirectAttributes redirectAttributes) {
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
        model.addAttribute(LISTAR_TEMPLATE_PERGUNTA, templatePerguntaFacade.findAll());
    }

    private void onEdit(Long id, Long idAvaliacao, Model model){
        TemplateTopicoDTO templateTopicoDTO = this.templateTopicoFacade.findById(id);
        templateTopicoDTO.setIdAvaliacao(idAvaliacao);
        TemplateAvaliacaoDTO templateAvaliacaoDTO = this.templateAvaliacaoFacade.findById(idAvaliacao);
        model.addAttribute(templateAvaliacaoDTO);
        model.addAttribute(templateTopicoDTO);
        model.addAttribute(LISTAR_TEMPLATE_PERGUNTA,
                this.templatePerguntaFacade.findAllComCheckedPerguntasMarcadas(
                        templateTopicoDTO, templateAvaliacaoDTO));
    }

    /**
     * Método que redireciona o usuário para a tela de alterar.
     * @param id id
     * @param idAvaliacao idAvaliacao
     * @param model model
     * @return String
     */
    @RequestMapping(value = ActionConstant.ACTION_EDIT_CUSTOM)
    public String prepareUpdate(@PathVariable Long id, @PathVariable Long idAvaliacao, Model model){
        this.onEdit(id, idAvaliacao, model);
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
    public String prepareError(@PathVariable Long id, @PathVariable Long idAvaliacao, Model model){
        this.onErrorOrDetail(id, idAvaliacao, model);
        return getViewForm();
    }

    /**
     * Método usado para quando houver uma exceção na tela de form e também reaproveitado para ser usado no momento de detalhar.
     * @param id id
     * @param idAvaliacao idAvaliacao
     * @param model model
     */
    private void onErrorOrDetail(Long id, Long idAvaliacao, Model model){
        TemplateTopicoDTO templateTopicoDTO = this.templateTopicoFacade.findById(id);
        templateTopicoDTO.setIdAvaliacao(idAvaliacao);
        model.addAttribute(templateTopicoDTO);
        model.addAttribute(this.templateAvaliacaoFacade.findById(idAvaliacao));
        this.onLoadView(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRedirectViewEdit(){
        return REDIRECT_LIST + getPathView() + ActionConstant.ACTION_EDIT_CUSTOM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRedirectViewError(){
        return REDIRECT_LIST + getPathView() + ActionConstant.ACTION_ERROR_CUSTOM;
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
    protected ITemplateTopicoFacade getFacade() {
        return templateTopicoFacade;
    }

}
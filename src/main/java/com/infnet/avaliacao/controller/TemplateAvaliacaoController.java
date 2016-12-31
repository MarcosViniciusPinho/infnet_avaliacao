package com.infnet.avaliacao.controller;

import com.infnet.avaliacao.business.facade.ITemplateAvaliacaoFacade;
import com.infnet.avaliacao.business.facade.ITemplateTopicoFacade;
import com.infnet.avaliacao.dto.impl.TemplateAvaliacaoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

/**
 * Classe responsável pela captura das informações na view.
 */
@Controller
@RequestMapping(value = TemplateAvaliacaoController.PATH_TEMPLATE_AVALIACAO)
public class TemplateAvaliacaoController extends TemplateController<TemplateAvaliacaoDTO>{

    protected static final String PATH_TEMPLATE_AVALIACAO = "/template/avaliacao";
    private static final String LISTAR_TEMPLATE_AVALIACAO = "listarTemplateAvaliacao";
    private static final String LISTAR_TEMPLATE_TOPICO = "listarTemplateTopico";

    @Resource
    private ITemplateAvaliacaoFacade templateAvaliacaoFacade;

    @Resource
    private ITemplateTopicoFacade templateTopicoFacade;

    /**
     * {@inheritDoc}
     */
    @Override
    protected ModelAndView onList(){
        ModelAndView mv = new ModelAndView(getViewList());
        mv.addObject(LISTAR_TEMPLATE_AVALIACAO, this.templateAvaliacaoFacade.findAll());
        return mv;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onForm(TemplateAvaliacaoDTO entity, Model model, RedirectAttributes redirectAttributes) {
        List<Long> idsTopicosSelecionados = entity.getIdsTemplateTopicoSelecionados();
        entity.setTemplateTopicoDTOList(
                this.templateTopicoFacade.getListaTemplatesTopicosPorId(idsTopicosSelecionados));
        model.addAttribute(LISTAR_TEMPLATE_TOPICO, templateTopicoFacade.findAll());
        redirectAttributes.addAttribute("id", entity.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onErrorOrDetail(Long id, Model model){
        model.addAttribute(this.templateAvaliacaoFacade.findById(id));
        this.onLoadView(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onLoadView(Model model){
        model.addAttribute(LISTAR_TEMPLATE_TOPICO, templateTopicoFacade.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onEdit(Long id, Model model){
        this.onLoadView(model);
        TemplateAvaliacaoDTO templateAvaliacaoDTO = this.templateAvaliacaoFacade.findById(id);
        model.addAttribute(templateAvaliacaoDTO.carregarTopicosCadastradosParaFicarSelecionados());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPathView() {
        return PATH_TEMPLATE_AVALIACAO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ITemplateAvaliacaoFacade getFacade() {
        return templateAvaliacaoFacade;
    }

}
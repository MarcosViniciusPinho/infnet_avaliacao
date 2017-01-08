package com.infnet.avaliacao.controller;

import com.infnet.avaliacao.business.facade.ITemplatePerguntaFacade;
import com.infnet.avaliacao.business.facade.ITemplateTopicoFacade;
import com.infnet.avaliacao.controller.util.PathConstant;
import com.infnet.avaliacao.dto.impl.TemplatePerguntaDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 * Classe responsável pela captura das informações na view.
 */
@Controller
@RequestMapping(value = PathConstant.PATH_TEMPLATE_PERGUNTA)
public class TemplatePerguntaController extends TemplateController<TemplatePerguntaDTO>{

    private static final String LISTAR_TEMPLATE_AVALIACAO = "listarTemplateAvaliacao";
    private static final String LISTAR_TEMPLATE_TOPICO = "listarTemplateTopico";

    @Resource
    private ITemplatePerguntaFacade templatePerguntaFacade;

    @Resource
    private ITemplateTopicoFacade templateTopicoFacade;

    /**
     * {@inheritDoc}
     */
    @Override
    protected ModelAndView onList(){
        ModelAndView mv = new ModelAndView(getViewList());
        mv.addObject(LISTAR_TEMPLATE_AVALIACAO, this.templatePerguntaFacade.findAll());
        return mv;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onForm(TemplatePerguntaDTO entity, Model model, RedirectAttributes redirectAttributes) {
//        List<Long> idsTopicosSelecionados = entity.getIdsTemplateTopicoSelecionados();
//        entity.setTemplateTopicoDTOList(
//                this.templateTopicoFacade.getListaTemplatesTopicosPorId(idsTopicosSelecionados));
        model.addAttribute(LISTAR_TEMPLATE_TOPICO, templateTopicoFacade.findAll());
        redirectAttributes.addAttribute("id", entity.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onErrorOrDetail(Long id, Model model){
        model.addAttribute(this.templatePerguntaFacade.findById(id));
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
        TemplatePerguntaDTO templatePerguntaDTO = this.templatePerguntaFacade.findById(id);
//        model.addAttribute(templatePerguntaDTO.carregarTopicosCadastradosParaFicarSelecionados());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPathView() {
        return PathConstant.PATH_TEMPLATE_PERGUNTA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ITemplatePerguntaFacade getFacade() {
        return templatePerguntaFacade;
    }

}
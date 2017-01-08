package com.infnet.avaliacao.controller;

import com.infnet.avaliacao.business.facade.ITemplatePerguntaFacade;
import com.infnet.avaliacao.business.facade.ITemplateTopicoFacade;
import com.infnet.avaliacao.controller.util.PathConstant;
import com.infnet.avaliacao.dto.impl.TemplateAvaliacaoTopicoPerguntaDTO;
import com.infnet.avaliacao.dto.impl.TemplatePerguntaDTO;
import com.infnet.avaliacao.dto.impl.TemplateTopicoDTO;
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
@RequestMapping(value = PathConstant.PATH_TEMPLATE_TOPICO)
public class TemplateTopicoController extends TemplateController<TemplateTopicoDTO>{

    private static final String LISTAR_TEMPLATE_PERGUNTA = "listarTemplatePergunta";

    @Resource
    private ITemplateTopicoFacade templateTopicoFacade;

    @Resource
    private ITemplatePerguntaFacade templatePerguntaFacade;

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
        entity.setTemplateAvaliacaoTopicoPerguntaDTOList(
                TemplateAvaliacaoTopicoPerguntaDTO.produceAssociativeClass(templatePerguntaDTOList, entity));
        model.addAttribute(LISTAR_TEMPLATE_PERGUNTA, templatePerguntaFacade.findAll());
        redirectAttributes.addAttribute("id", entity.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onErrorOrDetail(Long id, Model model){
        model.addAttribute(this.templateTopicoFacade.findById(id));
        this.onLoadView(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onLoadView(Model model){
        model.addAttribute(LISTAR_TEMPLATE_PERGUNTA, templatePerguntaFacade.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onEdit(Long id, Model model){
        this.onLoadView(model);
        TemplateTopicoDTO templateTopicoDTO = this.templateTopicoFacade.findById(id);
        model.addAttribute(templateTopicoDTO.carregarPerguntasCadastradosParaFicarSelecionados());
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
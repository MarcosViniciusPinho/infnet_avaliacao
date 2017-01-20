package com.infnet.avaliacao.controller.wrapper;

import com.infnet.avaliacao.dto.impl.TemplatePerguntaDTO;
import com.infnet.avaliacao.dto.impl.TemplateTopicoDTO;

import java.util.List;

public class PerguntaAssociadaWrapper {

    private TemplateTopicoDTO templateTopicoDTO;
    private List<TemplatePerguntaDTO> templatePerguntaDTOList;

    public TemplateTopicoDTO getTemplateTopicoDTO() {
        return templateTopicoDTO;
    }

    public void setTemplateTopicoDTO(TemplateTopicoDTO templateTopicoDTO) {
        this.templateTopicoDTO = templateTopicoDTO;
    }

    public List<TemplatePerguntaDTO> getTemplatePerguntaDTOList() {
        return templatePerguntaDTOList;
    }

    public void setTemplatePerguntaDTOList(List<TemplatePerguntaDTO> templatePerguntaDTOList) {
        this.templatePerguntaDTOList = templatePerguntaDTOList;
    }
}

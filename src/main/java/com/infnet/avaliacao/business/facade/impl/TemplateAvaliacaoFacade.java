package com.infnet.avaliacao.business.facade.impl;

import com.infnet.avaliacao.business.facade.ITemplateAvaliacaoFacade;
import com.infnet.avaliacao.business.service.ITemplateAvaliacaoService;
import com.infnet.avaliacao.dto.TemplateAvaliacaoDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class TemplateAvaliacaoFacade implements ITemplateAvaliacaoFacade {

    @Resource
    private ITemplateAvaliacaoService templateAvaliacaoService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TemplateAvaliacaoDTO> findAll() {
        return this.templateAvaliacaoService.findAll();
    }

}
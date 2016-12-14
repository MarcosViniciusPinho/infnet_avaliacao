package com.infnet.avaliacao.business.facade.impl;

import com.infnet.avaliacao.business.facade.ITemplateAvaliacaoFacade;
import com.infnet.avaliacao.business.service.ITemplateAvaliacaoService;
import com.infnet.avaliacao.dto.TemplateAvaliacaoDTO;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
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

    @Override
    public void save(TemplateAvaliacaoDTO dto) {
        templateAvaliacaoService.save(dto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        this.templateAvaliacaoService.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TemplateAvaliacaoDTO findById(Long id) {
        ParameterExceptionUtil.validateObjectNull(id);
        return this.templateAvaliacaoService.findById(id);
    }
}
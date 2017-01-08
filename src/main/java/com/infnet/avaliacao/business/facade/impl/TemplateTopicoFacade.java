package com.infnet.avaliacao.business.facade.impl;

import com.infnet.avaliacao.business.facade.ITemplateTopicoFacade;
import com.infnet.avaliacao.business.service.ITemplateTopicoService;
import com.infnet.avaliacao.dto.impl.TemplateTopicoDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class TemplateTopicoFacade implements ITemplateTopicoFacade {

    @Resource
    private ITemplateTopicoService templateTopicoService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TemplateTopicoDTO> findAll() {
        return TemplateTopicoDTO.convertListEntityToListDto(
                this.templateTopicoService.findAll());
    }

    @Override
    public void save(TemplateTopicoDTO dto) {
        templateTopicoService.save(dto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        this.templateTopicoService.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TemplateTopicoDTO findById(Long id) {
        return TemplateTopicoDTO.toDto(this.templateTopicoService.findById(id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TemplateTopicoDTO> getListaTemplatesTopicosPorId(List<Long> idsTemplateTopico) {
        return TemplateTopicoDTO.convertListEntityToListDto(
                this.templateTopicoService.getListaTemplatesTopicosPorId(idsTemplateTopico));
    }
}
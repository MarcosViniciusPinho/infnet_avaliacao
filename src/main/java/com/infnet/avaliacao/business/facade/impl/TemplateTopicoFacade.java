package com.infnet.avaliacao.business.facade.impl;

import com.infnet.avaliacao.business.facade.ITemplateTopicoFacade;
import com.infnet.avaliacao.business.service.ITemplateAvaliacaoTopicoPerguntaService;
import com.infnet.avaliacao.business.service.ITemplateTopicoService;
import com.infnet.avaliacao.dto.impl.TemplateTopicoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Resource
    private ITemplateAvaliacaoTopicoPerguntaService templateAvaliacaoTopicoPerguntaService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TemplateTopicoDTO> findAll() {
        return TemplateTopicoDTO.convertListEntityToListDto(
                this.templateTopicoService.findAll());
    }

    @Override
    public Page<TemplateTopicoDTO> findAllPaginated(Pageable pageable) {
        return TemplateTopicoDTO.convertPageEntityToPageDto(this.templateTopicoService.findAllPaginated(pageable), pageable);
    }

    @Override
    public void save(TemplateTopicoDTO dto) {
        this.templateTopicoService.save(dto);
        this.templateAvaliacaoTopicoPerguntaService.save(dto.getTemplateAvaliacaoTopicoPerguntaDTOList());
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
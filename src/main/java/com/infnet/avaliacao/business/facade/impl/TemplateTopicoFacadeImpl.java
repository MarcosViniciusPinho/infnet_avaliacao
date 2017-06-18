package com.infnet.avaliacao.business.facade.impl;

import com.infnet.avaliacao.business.facade.TemplateTopicoFacade;
import com.infnet.avaliacao.business.service.TemplateAvaliacaoTopicoPerguntaService;
import com.infnet.avaliacao.business.service.TemplateTopicoService;
import com.infnet.avaliacao.dto.impl.TemplateTopicoDTO;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class TemplateTopicoFacadeImpl implements TemplateTopicoFacade {

    @Resource
    private TemplateTopicoService templateTopicoService;

    @Resource
    private TemplateAvaliacaoTopicoPerguntaService templateAvaliacaoTopicoPerguntaService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TemplateTopicoDTO> findAll() {
        return TemplateTopicoDTO.convertListEntityToListDto(
                this.templateTopicoService.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<TemplateTopicoDTO> findAllPaginated(Pageable pageable) {
        ParameterExceptionUtil.validateObjectNull(pageable);
        return TemplateTopicoDTO.convertPageEntityToPageDto(this.templateTopicoService.findAllPaginated(pageable), pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(TemplateTopicoDTO dto) {
        ParameterExceptionUtil.validateObjectNull(dto);
        ParameterExceptionUtil.validateObjectNull(dto.getTemplateAvaliacaoTopicoPerguntaDTOList());
        this.templateTopicoService.save(dto);
        this.templateAvaliacaoTopicoPerguntaService.save(dto.getTemplateAvaliacaoTopicoPerguntaDTOList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TemplateTopicoDTO findById(Long id) {
        ParameterExceptionUtil.validateObjectNull(id);
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
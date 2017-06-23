package com.infnet.avaliacao.business.facade.impl;

import com.infnet.avaliacao.business.facade.TemplateAvaliacaoFacade;
import com.infnet.avaliacao.business.service.TemplateAvaliacaoService;
import com.infnet.avaliacao.dto.impl.TemplateAvaliacaoDTO;
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
public class TemplateAvaliacaoFacadeImpl implements TemplateAvaliacaoFacade {

    @Resource
    private TemplateAvaliacaoService templateAvaliacaoService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TemplateAvaliacaoDTO> findAll() {
        return TemplateAvaliacaoDTO.convertListEntityToListDto(this.templateAvaliacaoService.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<TemplateAvaliacaoDTO> findAllPaginated(Pageable pageable) {
        ParameterExceptionUtil.validateObjectNull(pageable);
        return TemplateAvaliacaoDTO.convertPageEntityToPageDto(this.templateAvaliacaoService.findAllPaginated(pageable), pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(TemplateAvaliacaoDTO dto) {
        ParameterExceptionUtil.validateObjectNull(dto);
        templateAvaliacaoService.save(dto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TemplateAvaliacaoDTO findById(Long id) {
        ParameterExceptionUtil.validateObjectNull(id);
        return TemplateAvaliacaoDTO.toDto(this.templateAvaliacaoService.findById(id));
    }
}
package com.infnet.avaliacao.business.facade.impl;

import com.infnet.avaliacao.business.facade.TemplateAvaliacaoFacade;
import com.infnet.avaliacao.business.service.ITemplateAvaliacaoService;
import com.infnet.avaliacao.dto.impl.TemplateAvaliacaoDTO;
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
    private ITemplateAvaliacaoService templateAvaliacaoService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TemplateAvaliacaoDTO> findAll() {
        return TemplateAvaliacaoDTO.convertListEntityToListDto(this.templateAvaliacaoService.findAll());
    }

    @Override
    public Page<TemplateAvaliacaoDTO> findAllPaginated(Pageable pageable) {
        return TemplateAvaliacaoDTO.convertPageEntityToPageDto(this.templateAvaliacaoService.findAllPaginated(pageable), pageable);
    }

    @Override
    public void save(TemplateAvaliacaoDTO dto) {
        templateAvaliacaoService.save(dto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TemplateAvaliacaoDTO findById(Long id) {
        return TemplateAvaliacaoDTO.toDto(this.templateAvaliacaoService.findById(id));
    }
}
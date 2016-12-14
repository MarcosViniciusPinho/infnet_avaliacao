package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.business.service.ITemplateAvaliacaoService;
import com.infnet.avaliacao.dto.TemplateAvaliacaoDTO;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import com.infnet.avaliacao.persistence.ITemplateAvaliacaoDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class TemplateAvaliacaoService implements ITemplateAvaliacaoService {

    @Resource
    private ITemplateAvaliacaoDAO templateAvaliacaoDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TemplateAvaliacaoDTO> findAll() {
        return TemplateAvaliacaoDTO.convertListEntityToListDto(this.templateAvaliacaoDao.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(TemplateAvaliacaoDTO templateAvaliacaoDTO) {
        this.validate(templateAvaliacaoDTO);
        this.templateAvaliacaoDao.save(templateAvaliacaoDTO.toEntity());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        ParameterExceptionUtil.validateObjectNull(id);
        this.templateAvaliacaoDao.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TemplateAvaliacaoDTO findById(Long id) {
        ParameterExceptionUtil.validateObjectNull(id);
        return TemplateAvaliacaoDTO.toDto(this.templateAvaliacaoDao.getOne(id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(TemplateAvaliacaoDTO dto) {
    }

}
package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.business.service.ITemplateAvaliacaoService;
import com.infnet.avaliacao.dto.TemplateAvaliacaoDTO;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import com.infnet.avaliacao.persistence.ITemplateAvaliacaoDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class TemplateAvaliacaoService implements ITemplateAvaliacaoService {

    @Resource
    private ITemplateAvaliacaoDao templateAvaliacaoDao;

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
    public void delete(Long id) {
        ParameterExceptionUtil.validateObjectNull(id);
        this.templateAvaliacaoDao.delete(id);
    }

}
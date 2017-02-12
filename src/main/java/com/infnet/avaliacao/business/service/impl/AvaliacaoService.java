package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.business.service.IAvaliacaoService;
import com.infnet.avaliacao.dto.impl.AvaliacaoDTO;
import com.infnet.avaliacao.persistence.IAvaliacaoDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * {@inheritDoc}
 */
@Service
public class AvaliacaoService implements IAvaliacaoService {

    @Resource
    private IAvaliacaoDAO avaliacaoDAO;

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(AvaliacaoDTO avaliacaoDTO) {
        this.validate(avaliacaoDTO);
        this.avaliacaoDAO.save(avaliacaoDTO.toEntity());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(AvaliacaoDTO avaliacaoDTO) {}

}
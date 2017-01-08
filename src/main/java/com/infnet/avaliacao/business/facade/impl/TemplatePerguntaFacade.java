package com.infnet.avaliacao.business.facade.impl;

import com.infnet.avaliacao.business.facade.ITemplatePerguntaFacade;
import com.infnet.avaliacao.business.service.ITemplatePerguntaService;
import com.infnet.avaliacao.dto.impl.TemplatePerguntaDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class TemplatePerguntaFacade implements ITemplatePerguntaFacade {

    @Resource
    private ITemplatePerguntaService templatePerguntaService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TemplatePerguntaDTO> findAll() {
        return TemplatePerguntaDTO.convertListEntityToListDto(this.templatePerguntaService.findAll());
    }

    @Override
    public void save(TemplatePerguntaDTO dto) {
        this.templatePerguntaService.save(dto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        this.templatePerguntaService.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TemplatePerguntaDTO findById(Long id) {
        return TemplatePerguntaDTO.toDto(this.templatePerguntaService.findById(id));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<TemplatePerguntaDTO> getListaTemplatesPerguntasPorId(List<Long> idsTemplateTopico) {
        return TemplatePerguntaDTO.convertListEntityToListDto(
                this.templatePerguntaService.getListaTemplatesPerguntasPorId(idsTemplateTopico));
    }
}
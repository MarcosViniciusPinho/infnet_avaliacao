package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.business.service.ITemplateTopicoService;
import com.infnet.avaliacao.dto.impl.TemplateTopicoDTO;
import com.infnet.avaliacao.entity.TemplateTopico;
import com.infnet.avaliacao.persistence.ITemplateTopicoDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class TemplateTopicoService extends CrudService<TemplateTopicoDTO, TemplateTopico> implements ITemplateTopicoService {

    @Resource
    private ITemplateTopicoDAO templateTopicoDAO;

    @Override
    public List<TemplateTopico> getListaTemplatesTopicosPorId(List<Long> idsTemplateTopico) {
        return this.templateTopicoDAO.findByIdIn(idsTemplateTopico);
    }
}
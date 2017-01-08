package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.business.service.ITemplatePerguntaService;
import com.infnet.avaliacao.dto.impl.TemplatePerguntaDTO;
import com.infnet.avaliacao.entity.TemplatePergunta;
import com.infnet.avaliacao.persistence.ITemplatePerguntaDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class TemplatePerguntaService extends CrudService<TemplatePerguntaDTO, TemplatePergunta> implements ITemplatePerguntaService {

    @Resource
    private ITemplatePerguntaDAO templatePerguntaDAO;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TemplatePergunta> getListaTemplatesPerguntasPorId(List<Long> idsTemplateTopico) {
        return this.templatePerguntaDAO.findByIdIn(idsTemplateTopico);
    }

}
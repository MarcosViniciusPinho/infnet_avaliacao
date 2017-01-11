package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.business.service.ITemplatePerguntaService;
import com.infnet.avaliacao.dto.impl.TemplateAvaliacaoDTO;
import com.infnet.avaliacao.dto.impl.TemplatePerguntaDTO;
import com.infnet.avaliacao.dto.impl.TemplateTopicoDTO;
import com.infnet.avaliacao.entity.TemplateAvaliacaoTopicoPergunta;
import com.infnet.avaliacao.entity.TemplatePergunta;
import com.infnet.avaliacao.persistence.ITemplateAvaliacaoTopicoPerguntaDAO;
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

    @Resource
    private ITemplateAvaliacaoTopicoPerguntaDAO templateAvaliacaoTopicoPerguntaDAO;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TemplatePergunta> getListaTemplatesPerguntasPorId(List<Long> idsTemplateTopico) {
        return this.templatePerguntaDAO.findByIdIn(idsTemplateTopico);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TemplatePerguntaDTO> findAllComCheckedPerguntasMarcadas(TemplateTopicoDTO templateTopicoDTO,
                                                                        TemplateAvaliacaoDTO templateAvaliacaoDTO) {
        List<TemplatePerguntaDTO> templatePerguntaDTOList = TemplatePerguntaDTO.convertListEntityToListDto(templatePerguntaDAO.findAll());
        for(TemplatePerguntaDTO templatePerguntaDTO : templatePerguntaDTOList){
            TemplateAvaliacaoTopicoPergunta templateAvaliacaoTopicoPergunta = this.templateAvaliacaoTopicoPerguntaDAO.
                    findByTemplateAvaliacaoAndTemplateTopicoAndTemplatePerguntaEquals(templateAvaliacaoDTO.toEntity(),
                            templateTopicoDTO.toEntity(), templatePerguntaDTO.toEntity());
            templatePerguntaDTO.setChecked(templateAvaliacaoTopicoPergunta != null ? Boolean.TRUE : Boolean.FALSE);
        }
        return templatePerguntaDTOList;
    }

}
package com.infnet.avaliacao.business.facade.impl;

import com.infnet.avaliacao.business.facade.TemplatePerguntaFacade;
import com.infnet.avaliacao.business.service.TemplateAvaliacaoTopicoPerguntaService;
import com.infnet.avaliacao.business.service.TemplatePerguntaService;
import com.infnet.avaliacao.dto.impl.TemplateAvaliacaoDTO;
import com.infnet.avaliacao.dto.impl.TemplateAvaliacaoTopicoPerguntaDTO;
import com.infnet.avaliacao.dto.impl.TemplatePerguntaDTO;
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
public class TemplatePerguntaFacadeImpl implements TemplatePerguntaFacade {

    @Resource
    private TemplatePerguntaService templatePerguntaService;

    @Resource
    private TemplateAvaliacaoTopicoPerguntaService templateAvaliacaoTopicoPerguntaService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TemplatePerguntaDTO> findAll() {
        return TemplatePerguntaDTO.convertListEntityToListDto(this.templatePerguntaService.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<TemplatePerguntaDTO> findAllPaginated(Pageable pageable) {
        ParameterExceptionUtil.validateObjectNull(pageable);
        return TemplatePerguntaDTO.convertPageEntityToPageDto(this.templatePerguntaService.findAllPaginated(pageable), pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(TemplatePerguntaDTO dto) {
        ParameterExceptionUtil.validateObjectNull(dto);
        this.templatePerguntaService.save(dto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TemplatePerguntaDTO findById(Long id) {
        ParameterExceptionUtil.validateObjectNull(id);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TemplateAvaliacaoTopicoPerguntaDTO> getListaPerguntasAssociadasAoTopicoPorAvaliacao(
            List<TemplatePerguntaDTO> templatePerguntaDTOList, TemplateTopicoDTO templateTopicoDTO, TemplateAvaliacaoDTO templateAvaliacaoDTO) {
        return this.templateAvaliacaoTopicoPerguntaService.produceAssociativeClass(templatePerguntaDTOList, templateTopicoDTO, templateAvaliacaoDTO);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<TemplatePerguntaDTO> findAllComCheckedPerguntasMarcadas(TemplateTopicoDTO templateTopicoDTO,
                                                                        TemplateAvaliacaoDTO templateAvaliacaoDTO,
                                                                        Pageable pageable) {
        Page<TemplatePerguntaDTO> templatePerguntaDTOList = this.findAllPaginated(pageable);
        return this.templatePerguntaService.findAllComCheckedPerguntasMarcadas(templateTopicoDTO, templateAvaliacaoDTO, templatePerguntaDTOList);
    }

}
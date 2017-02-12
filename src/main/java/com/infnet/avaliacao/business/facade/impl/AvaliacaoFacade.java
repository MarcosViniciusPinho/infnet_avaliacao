package com.infnet.avaliacao.business.facade.impl;

import com.infnet.avaliacao.business.facade.IAvaliacaoFacade;
import com.infnet.avaliacao.business.service.IAlunoService;
import com.infnet.avaliacao.business.service.IAvaliacaoService;
import com.infnet.avaliacao.business.service.ITemplateAvaliacaoService;
import com.infnet.avaliacao.business.service.ITurmaService;
import com.infnet.avaliacao.dto.impl.AlunoDTO;
import com.infnet.avaliacao.dto.impl.AvaliacaoDTO;
import com.infnet.avaliacao.dto.impl.TemplateAvaliacaoDTO;
import com.infnet.avaliacao.dto.impl.TurmaDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * {@inheritDoc}
 */
@Service
public class AvaliacaoFacade implements IAvaliacaoFacade {

    @Resource
    private IAvaliacaoService avaliacaoService;

    @Resource
    private IAlunoService alunoService;

    @Resource
    private ITurmaService turmaService;

    @Resource
    private ITemplateAvaliacaoService templateAvaliacaoService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(AvaliacaoDTO avaliacaoDTO) {
        this.avaliacaoService.save(avaliacaoDTO);
    }

    public AvaliacaoDTO popularAlunoAndTurmaParaAvaliacao(Long cpf, Long idTurma){
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
        Long idTemplateAvaliacao = this.turmaService.findTemplateAvaliacaoTurmaById(idTurma);
        avaliacaoDTO.setAlunoDTO(
                AlunoDTO.toDto(this.alunoService.findByCpf(cpf)));
        avaliacaoDTO.setTurmaDTO(
                TurmaDTO.toDto(this.turmaService.findById(idTurma)));
        avaliacaoDTO.setTemplateAvaliacaoDTO(
                TemplateAvaliacaoDTO.toDto(
                        this.templateAvaliacaoService.findById(idTemplateAvaliacao)));
        return avaliacaoDTO;
    }

    public boolean isExisteCpf(Long cpf){
        return this.alunoService.findByCpf(cpf) != null ? Boolean.TRUE : Boolean.FALSE;
    }

}
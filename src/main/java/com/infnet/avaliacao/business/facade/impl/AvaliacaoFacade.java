package com.infnet.avaliacao.business.facade.impl;

import com.infnet.avaliacao.business.facade.IAvaliacaoFacade;
import com.infnet.avaliacao.business.service.IAlunoService;
import com.infnet.avaliacao.business.service.IAvaliacaoService;
import com.infnet.avaliacao.business.service.ITurmaService;
import com.infnet.avaliacao.dto.impl.AlunoDTO;
import com.infnet.avaliacao.dto.impl.AvaliacaoDTO;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(AvaliacaoDTO avaliacaoDTO) {
        this.avaliacaoService.save(avaliacaoDTO);
    }

    public AvaliacaoDTO popularAlunoAndTurmaParaAvaliacao(Long cpf, Long idTurma){
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
        avaliacaoDTO.setAlunoDTO(
                AlunoDTO.toDto(this.alunoService.findByCpf(cpf)));
        avaliacaoDTO.setTurmaDTO(
                TurmaDTO.toDto(this.turmaService.findById(idTurma)));
        return avaliacaoDTO;
    }

    public boolean isExisteCpf(Long cpf){
        return this.alunoService.findByCpf(cpf) != null ? Boolean.TRUE : Boolean.FALSE;
    }

}
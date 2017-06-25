package com.infnet.avaliacao.business.facade.impl;

import com.infnet.avaliacao.business.facade.AvaliacaoFacade;
import com.infnet.avaliacao.business.service.*;
import com.infnet.avaliacao.dto.impl.*;
import com.infnet.avaliacao.entity.Avaliacao;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class AvaliacaoFacadeImpl implements AvaliacaoFacade {

    @Resource
    private AvaliacaoService avaliacaoService;

    @Resource
    private AlunoService alunoService;

    @Resource
    private TurmaService turmaService;

    @Resource
    private TemplateAvaliacaoService templateAvaliacaoService;

    @Resource
    private RespostaService respostaService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(AvaliacaoDTO avaliacaoDTO) {
        ParameterExceptionUtil.validateObjectNull(avaliacaoDTO);
        AvaliacaoDTO avaliacaoDTOBase =
                AvaliacaoDTO.toDto(this.avaliacaoService.save(avaliacaoDTO));
        avaliacaoDTOBase.setRespostasSelecionadasComPerguntas(
                avaliacaoDTO.getRespostasSelecionadasComPerguntas());
        this.respostaService.save(this.popularListaResposta(avaliacaoDTOBase));
    }

    private List<RespostaDTO> popularListaResposta(AvaliacaoDTO avaliacaoDTO){
        Avaliacao avaliacao = avaliacaoDTO.toEntity();
        List<RespostaDTO> respostaDTOList = new ArrayList<>();
        for(String respostaComPerguntaAssociada : avaliacaoDTO.getRespostasSelecionadasComPerguntas()){
            String valorResposta = avaliacaoDTO.getValorResposta(respostaComPerguntaAssociada);
            Long idTemplatePergunta = avaliacaoDTO.getIdTemplatePergunta(respostaComPerguntaAssociada);
            respostaDTOList.add(this.respostaService.popularResposta(valorResposta, idTemplatePergunta, avaliacao));
        }
        return respostaDTOList;
    }

    public AvaliacaoDTO popularAlunoAndTurmaParaAvaliacao(Long cpf, Long idTurma){
        ParameterExceptionUtil.validateObjectNull(cpf);
        ParameterExceptionUtil.validateObjectNull(idTurma);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void verificarParametrosEnviadosAoCarregarPagina(Long cpf, Long id){
        this.turmaService.verificarSeExisteTurma(id);
        this.alunoService.verificarSeExisteCpf(cpf);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void verificarSeAlunoJaRespondeuAvaliacao(TurmaDTO turmaDTO, AlunoDTO alunoDTO) {
        this.avaliacaoService.verificarSeAlunoJaRespondeuAvaliacao(turmaDTO.toEntity(), alunoDTO.toEntity());
    }
}
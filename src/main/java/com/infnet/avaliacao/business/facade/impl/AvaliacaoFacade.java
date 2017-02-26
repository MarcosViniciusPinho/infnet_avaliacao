package com.infnet.avaliacao.business.facade.impl;

import com.infnet.avaliacao.business.facade.IAvaliacaoFacade;
import com.infnet.avaliacao.business.service.*;
import com.infnet.avaliacao.dto.impl.*;
import com.infnet.avaliacao.entity.Avaliacao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @Resource
    private ITemplatePerguntaService templatePerguntaService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(AvaliacaoDTO avaliacaoDTO) {
        avaliacaoDTO.setRespostaDTOList(this.criarListaResposta(avaliacaoDTO));
        this.avaliacaoService.save(avaliacaoDTO);
    }

    private List<RespostaDTO> criarListaResposta(AvaliacaoDTO avaliacaoDTO){
        Avaliacao avaliacao = avaliacaoDTO.toEntity();
        List<RespostaDTO> respostaDTOList = new ArrayList<>();
        for(String respostaComPerguntaAssociada : avaliacaoDTO.getRespostasSelecionadasComPerguntas()){
            String resposta = avaliacaoDTO.getRespostasAndPerguntasSeparados(respostaComPerguntaAssociada)[0];
            Long idTemplatePergunta = Long.parseLong(
                    avaliacaoDTO.getRespostasAndPerguntasSeparados(
                            respostaComPerguntaAssociada)[1]);
            respostaDTOList.add(this.popularResposta(resposta, idTemplatePergunta, avaliacao));
        }
        return respostaDTOList;
    }

    private RespostaDTO popularResposta(String resposta, Long idTemplatePergunta, Avaliacao avaliacao){
        TemplatePerguntaDTO templatePerguntaDTO = TemplatePerguntaDTO.toDto(
                this.templatePerguntaService.findById(idTemplatePergunta));
        RespostaDTO respostaDTO = new RespostaDTO();
        respostaDTO.setValor(resposta);
        respostaDTO.setTemplatePerguntaDTO(templatePerguntaDTO);
        respostaDTO.setAvaliacao(avaliacao);
        return respostaDTO;
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
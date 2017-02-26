package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.business.service.IRespostaService;
import com.infnet.avaliacao.dto.impl.AvaliacaoDTO;
import com.infnet.avaliacao.dto.impl.RespostaDTO;
import com.infnet.avaliacao.dto.impl.TemplatePerguntaDTO;
import com.infnet.avaliacao.entity.Avaliacao;
import com.infnet.avaliacao.persistence.IRespostaDAO;
import com.infnet.avaliacao.persistence.ITemplatePerguntaDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class RespostaService implements IRespostaService {

    @Resource
    private IRespostaDAO respostaDAO;

    @Resource
    private ITemplatePerguntaDAO templatePerguntaDAO;

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(AvaliacaoDTO avaliacaoDTO) {
        this.respostaDAO.save(
                RespostaDTO.convertListDtoToListEntity(
                        this.criarListaResposta(avaliacaoDTO)));
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
                this.templatePerguntaDAO.getOne(idTemplatePergunta));
        RespostaDTO respostaDTO = new RespostaDTO();
        respostaDTO.setValor(resposta);
        respostaDTO.setTemplatePerguntaDTO(templatePerguntaDTO);
        respostaDTO.setAvaliacao(avaliacao);
        return respostaDTO;
    }

}
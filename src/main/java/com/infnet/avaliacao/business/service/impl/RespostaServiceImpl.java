package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.business.service.RespostaService;
import com.infnet.avaliacao.dto.impl.RespostaDTO;
import com.infnet.avaliacao.dto.impl.TemplatePerguntaDTO;
import com.infnet.avaliacao.entity.Avaliacao;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import com.infnet.avaliacao.repository.RespostaRepository;
import com.infnet.avaliacao.repository.TemplatePerguntaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class RespostaServiceImpl implements RespostaService {

    @Resource
    private RespostaRepository respostaRepository;

    @Resource
    private TemplatePerguntaRepository templatePerguntaRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(List<RespostaDTO> respostaDTOList) {
        ParameterExceptionUtil.validateObjectNull(respostaDTOList);
        this.respostaRepository.save(
               RespostaDTO.convertListDtoToListEntity(respostaDTOList));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RespostaDTO popularResposta(String resposta, Long idTemplatePergunta, Avaliacao avaliacao){
        ParameterExceptionUtil.validateObjectNull(resposta);
        ParameterExceptionUtil.validateObjectNull(idTemplatePergunta);
        ParameterExceptionUtil.validateObjectNull(avaliacao);
        TemplatePerguntaDTO templatePerguntaDTO = TemplatePerguntaDTO.toDto(
                this.templatePerguntaRepository.getOne(idTemplatePergunta));
        RespostaDTO respostaDTO = new RespostaDTO();
        respostaDTO.setValor(resposta);
        respostaDTO.setTemplatePerguntaDTO(templatePerguntaDTO);
        respostaDTO.setAvaliacao(avaliacao);
        return respostaDTO;
    }

}
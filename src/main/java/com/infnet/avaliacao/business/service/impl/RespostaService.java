package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.business.service.IRespostaService;
import com.infnet.avaliacao.dto.impl.RespostaDTO;
import com.infnet.avaliacao.dto.impl.TemplatePerguntaDTO;
import com.infnet.avaliacao.entity.Avaliacao;
import com.infnet.avaliacao.persistence.IRespostaDAO;
import com.infnet.avaliacao.persistence.ITemplatePerguntaDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public void save(List<RespostaDTO> respostaDTOList) {
        this.respostaDAO.save(
               RespostaDTO.convertListDtoToListEntity(respostaDTOList));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RespostaDTO popularResposta(String resposta, Long idTemplatePergunta, Avaliacao avaliacao){
        TemplatePerguntaDTO templatePerguntaDTO = TemplatePerguntaDTO.toDto(
                this.templatePerguntaDAO.getOne(idTemplatePergunta));
        RespostaDTO respostaDTO = new RespostaDTO();
        respostaDTO.setValor(resposta);
        respostaDTO.setTemplatePerguntaDTO(templatePerguntaDTO);
        respostaDTO.setAvaliacao(avaliacao);
        return respostaDTO;
    }

}
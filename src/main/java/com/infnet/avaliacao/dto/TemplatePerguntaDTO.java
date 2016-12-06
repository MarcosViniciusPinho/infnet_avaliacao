package com.infnet.avaliacao.dto;

import com.infnet.avaliacao.dto.domain.MultiplaEscolhaEnum;
import com.infnet.avaliacao.entity.TemplatePergunta;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TemplatePerguntaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String questao;

    /**
     * Método que converte uma entidade para um dto.
     * @param templatePergunta templatePergunta
     * @return TemplatePerguntaDTO
     */
    public static TemplatePerguntaDTO toDto(TemplatePergunta templatePergunta){
        ParameterExceptionUtil.validateObjectNull(templatePergunta);
        TemplatePerguntaDTO templatePerguntaDTO = new TemplatePerguntaDTO();
        templatePerguntaDTO.setId(templatePergunta.getId());
        templatePerguntaDTO.setQuestao(templatePergunta.getQuestao());
        return templatePerguntaDTO;
    }

    /**
     * Método que converte uma lista de entidade para uma lista de dto.
     * @param entities entities
     * @return List<TemplatePerguntaDTO>
     */
    public static List<TemplatePerguntaDTO> convertListEntityToListDto(List<TemplatePergunta> entities){
        List<TemplatePerguntaDTO> lista = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(entities)){
            for(TemplatePergunta templatePergunta : entities){
                lista.add(toDto(templatePergunta));
            }
        }
        return lista;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestao() {
        return questao;
    }

    public void setQuestao(String questao) {
        this.questao = questao;
    }

    /**
     * Exibe as opções de múltipla escolha para as peguntas
     * @return List<MultiplaEscolhaEnum>
     */
    public List<MultiplaEscolhaEnum> getMultiplaEscolhaEnumList() {
        return Arrays.asList(MultiplaEscolhaEnum.values());
    }

}

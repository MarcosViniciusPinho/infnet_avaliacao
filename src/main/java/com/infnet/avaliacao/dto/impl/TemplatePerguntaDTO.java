package com.infnet.avaliacao.dto.impl;

import com.infnet.avaliacao.dto.IDTO;
import com.infnet.avaliacao.dto.domain.MultiplaEscolhaEnum;
import com.infnet.avaliacao.entity.TemplatePergunta;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;

import java.util.Arrays;
import java.util.List;

public class TemplatePerguntaDTO implements IDTO<TemplatePergunta> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String questao;

    /**
     * {@inheritDoc}
     */
    @Override
    public TemplatePergunta toEntity(){
        TemplatePergunta templatePergunta = new TemplatePergunta();
        templatePergunta.setId(this.getId());
        templatePergunta.setQuestao(this.getQuestao());
        return templatePergunta;
    }

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

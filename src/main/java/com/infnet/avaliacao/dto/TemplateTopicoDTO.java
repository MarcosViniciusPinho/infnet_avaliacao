package com.infnet.avaliacao.dto;

import com.infnet.avaliacao.entity.TemplatePergunta;
import com.infnet.avaliacao.entity.TemplateTopico;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TemplateTopicoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String enunciado;

    private List<TemplatePergunta> templatePerguntaList;


    /**
     * Método que converte uma entidade para um dto.
     * @param TemplateTopico TemplateTopico
     * @return TemplateTopicoDTO
     */
    public static TemplateTopicoDTO toDto(TemplateTopico TemplateTopico){
        ParameterExceptionUtil.validateObjectNull(TemplateTopico);
        TemplateTopicoDTO TemplateTopicoDTO = new TemplateTopicoDTO();
        TemplateTopicoDTO.setId(TemplateTopico.getId());
        TemplateTopicoDTO.setEnunciado(TemplateTopico.getEnunciado());
        TemplateTopicoDTO.setTemplatePerguntaList(TemplateTopico.getTemplatePerguntaList());
        return TemplateTopicoDTO;
    }

    /**
     * Método que converte uma lista de entidade para uma lista de dto.
     * @param entities entities
     * @return List<TemplateTopicoDTO>
     */
    public static List<TemplateTopicoDTO> convertListEntityToListDto(List<TemplateTopico> entities){
        List<TemplateTopicoDTO> lista = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(entities)){
            for(TemplateTopico TemplateTopico : entities){
                lista.add(toDto(TemplateTopico));
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

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public List<TemplatePergunta> getTemplatePerguntaList() {
        return templatePerguntaList;
    }

    public void setTemplatePerguntaList(List<TemplatePergunta> templatePerguntaList) {
        this.templatePerguntaList = templatePerguntaList;
    }

}

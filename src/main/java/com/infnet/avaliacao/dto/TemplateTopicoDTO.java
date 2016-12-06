package com.infnet.avaliacao.dto;

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

    private List<TemplatePerguntaDTO> templatePerguntaDTOList;

    /**
     * Método que converte uma entidade para um dto.
     * @param templateTopico templateTopico
     * @return TemplateTopicoDTO
     */
    public static TemplateTopicoDTO toDto(TemplateTopico templateTopico){
        ParameterExceptionUtil.validateObjectNull(templateTopico);
        TemplateTopicoDTO templateTopicoDTO = new TemplateTopicoDTO();
        templateTopicoDTO.setId(templateTopico.getId());
        templateTopicoDTO.setEnunciado(templateTopico.getEnunciado());
        templateTopicoDTO.setTemplatePerguntaDTOList(
                TemplatePerguntaDTO.convertListEntityToListDto(
                        templateTopico.getTemplatePerguntaList()));
        return templateTopicoDTO;
    }

    /**
     * Método que converte uma lista de entidade para uma lista de dto.
     * @param entities entities
     * @return List<TemplateTopicoDTO>
     */
    public static List<TemplateTopicoDTO> convertListEntityToListDto(List<TemplateTopico> entities){
        List<TemplateTopicoDTO> lista = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(entities)){
            for(TemplateTopico templateTopico : entities){
                lista.add(toDto(templateTopico));
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

    public List<TemplatePerguntaDTO> getTemplatePerguntaDTOList() {
        return templatePerguntaDTOList;
    }

    public void setTemplatePerguntaDTOList(List<TemplatePerguntaDTO> templatePerguntaDTOList) {
        this.templatePerguntaDTOList = templatePerguntaDTOList;
    }
}

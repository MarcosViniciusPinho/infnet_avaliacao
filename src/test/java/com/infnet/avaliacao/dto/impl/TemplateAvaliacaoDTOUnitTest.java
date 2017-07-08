package com.infnet.avaliacao.dto.impl;

import com.infnet.avaliacao.entity.TemplateAvaliacao;
import com.infnet.avaliacao.entity.TemplateTopico;
import com.infnet.avaliacao.exception.NullParameterException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TemplateAvaliacaoDTOUnitTest {

    @Test
    public void testToEntity(){
        TemplateAvaliacao templateAvaliacao = this.createTemplateAvaliacao(3L);
        templateAvaliacao.setTemplateTopicoList(this.getTemplateTopicoList());

        TemplateAvaliacaoDTO templateAvaliacaoDTO = this.createTemplateAvaliacaoDTO(3L);
        templateAvaliacaoDTO.setTemplateTopicoDTOList(this.getTemplateTopicoDTOList());

        Assert.assertNotNull(templateAvaliacaoDTO.toEntity());
        Assert.assertEquals(templateAvaliacao, templateAvaliacaoDTO.toEntity());
    }

    @Test
    public void testToDto(){
        TemplateAvaliacao templateAvaliacao = this.createTemplateAvaliacao(3L);
        templateAvaliacao.setTemplateTopicoList(this.getTemplateTopicoList());

        TemplateAvaliacaoDTO templateAvaliacaoDTO = this.createTemplateAvaliacaoDTO(3L);
        templateAvaliacaoDTO.setTemplateTopicoDTOList(this.getTemplateTopicoDTOList());

        Assert.assertNotNull(TemplateAvaliacaoDTO.toDto(templateAvaliacao));
        Assert.assertEquals(templateAvaliacaoDTO, TemplateAvaliacaoDTO.toDto(templateAvaliacao));
    }

    @Test(expected = NullParameterException.class)
    public void testToDtoFailedTemplateAvaliacaoNull(){
        TemplateAvaliacaoDTO.toDto(null);
    }

    /**
     * Métodos foram criados para auxiliar nos testes; ou seja; diminuir a codificação dos mesmos.
     */

    private List<TemplateTopicoDTO> getTemplateTopicoDTOList(){
        List<TemplateTopicoDTO> templateTopicoDTOList = new ArrayList<>();
        templateTopicoDTOList.add(this.createTemplateTopicoDTO(2L));
        templateTopicoDTOList.add(this.createTemplateTopicoDTO(1L));
        templateTopicoDTOList.add(this.createTemplateTopicoDTO(3L));
        return templateTopicoDTOList;
    }

    private List<TemplateTopico> getTemplateTopicoList(){
        List<TemplateTopico> templateTopicoList = new ArrayList<>();
        templateTopicoList.add(this.createTemplateTopico(2L));
        templateTopicoList.add(this.createTemplateTopico(1L));
        templateTopicoList.add(this.createTemplateTopico(3L));
        return templateTopicoList;
    }

    private TemplateAvaliacaoDTO createTemplateAvaliacaoDTO(Long id){
        TemplateAvaliacaoDTO templateAvaliacaoDTO = new TemplateAvaliacaoDTO();
        templateAvaliacaoDTO.setId(id);
        templateAvaliacaoDTO.setTitulo("titulo");
        return templateAvaliacaoDTO;
    }

    private TemplateAvaliacao createTemplateAvaliacao(Long id){
        TemplateAvaliacao templateAvaliacao = new TemplateAvaliacao();
        templateAvaliacao.setId(id);
        templateAvaliacao.setTitulo("titulo");
        return templateAvaliacao;
    }

    private TemplateTopico createTemplateTopico(Long id){
        TemplateTopico templateTopico = new TemplateTopico();
        templateTopico.setId(id);
        return templateTopico;
    }

    private TemplateTopicoDTO createTemplateTopicoDTO(Long id){
        return TemplateTopicoDTO.toDto(this.createTemplateTopico(id));
    }

}

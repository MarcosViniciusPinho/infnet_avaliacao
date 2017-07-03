package com.infnet.avaliacao.dto.impl;

import com.infnet.avaliacao.entity.TemplateAvaliacao;
import com.infnet.avaliacao.entity.TemplateAvaliacaoTopicoPergunta;
import com.infnet.avaliacao.entity.TemplatePergunta;
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
public class TemplateTopicoDTOUnitTest {

    @Test
    public void testToEntity(){
        TemplateTopico templateTopico = this.createTemplateTopico(3L);
        TemplateTopicoDTO templateTopicoDTO = this.createTemplateTopicoDTO(3L);
        Assert.assertNotNull(templateTopicoDTO.toEntity());
        Assert.assertEquals(templateTopico, templateTopicoDTO.toEntity());
    }

    @Test
    public void testToDto(){
        TemplateTopico templateTopico = this.createTemplateTopico(3L);
        TemplateTopicoDTO templateTopicoDTO = this.createTemplateTopicoDTO(3L);
        Assert.assertNotNull(TemplateTopicoDTO.toDto(templateTopico));
        Assert.assertEquals(templateTopicoDTO, TemplateTopicoDTO.toDto(templateTopico));
    }

    @Test(expected = NullParameterException.class)
    public void testToDtoFailedAlunoNull(){
        TemplateTopicoDTO.toDto(null);
    }

    /**
     * Métodos foram criados para auxiliar nos testes; ou seja; diminuir a codificação dos mesmos.
     */

    private TemplateTopico createTemplateTopico(Long id){
        List<TemplateAvaliacaoTopicoPergunta> templateAvaliacaoTopicoPerguntaList = new ArrayList<>();
        templateAvaliacaoTopicoPerguntaList.add(this.createTemplateAvaliacaoTopicoPergunta(1L));
        templateAvaliacaoTopicoPerguntaList.add(this.createTemplateAvaliacaoTopicoPergunta(4L));
        templateAvaliacaoTopicoPerguntaList.add(this.createTemplateAvaliacaoTopicoPergunta(2L));

        TemplateTopico templateTopico = new TemplateTopico();
        templateTopico.setId(id);
        templateTopico.setEnunciado("Enunciado teste");
        templateTopico.setTemplateAvaliacaoTopicoPerguntaList(templateAvaliacaoTopicoPerguntaList);
        return templateTopico;
    }

    private TemplateTopicoDTO createTemplateTopicoDTO(Long id){
        List<TemplateAvaliacaoTopicoPerguntaDTO> templateAvaliacaoTopicoPerguntaDTOList = new ArrayList<>();
        templateAvaliacaoTopicoPerguntaDTOList.add(this.createTemplateAvaliacaoTopicoPerguntaDTO(1L));
        templateAvaliacaoTopicoPerguntaDTOList.add(this.createTemplateAvaliacaoTopicoPerguntaDTO(4L));
        templateAvaliacaoTopicoPerguntaDTOList.add(this.createTemplateAvaliacaoTopicoPerguntaDTO(2L));

        TemplateTopicoDTO templateTopicoDTO = new TemplateTopicoDTO();
        templateTopicoDTO.setId(id);
        templateTopicoDTO.setEnunciado("Enunciado teste");
        templateTopicoDTO.setTemplateAvaliacaoTopicoPerguntaDTOList(templateAvaliacaoTopicoPerguntaDTOList);
        return templateTopicoDTO;
    }

    private TemplateAvaliacaoTopicoPergunta createTemplateAvaliacaoTopicoPergunta(Long id){
        TemplateAvaliacaoTopicoPergunta templateAvaliacaoTopicoPergunta = new TemplateAvaliacaoTopicoPergunta();
        templateAvaliacaoTopicoPergunta.setId(id);
        templateAvaliacaoTopicoPergunta.setTemplateAvaliacao(this.createTemplateAvaliacao(2L));
        templateAvaliacaoTopicoPergunta.setTemplatePergunta(this.createTemplatePergunta(5L));
        return templateAvaliacaoTopicoPergunta;
    }

    private TemplateAvaliacaoTopicoPerguntaDTO createTemplateAvaliacaoTopicoPerguntaDTO(Long id){
        return TemplateAvaliacaoTopicoPerguntaDTO.toDto(this.createTemplateAvaliacaoTopicoPergunta(id));
    }

    private TemplateAvaliacao createTemplateAvaliacao(Long id){
        TemplateAvaliacao templateAvaliacao = new TemplateAvaliacao();
        templateAvaliacao.setId(id);
        return templateAvaliacao;
    }

    private TemplatePergunta createTemplatePergunta(Long id){
        TemplatePergunta templatePergunta = new TemplatePergunta();
        templatePergunta.setId(id);
        return templatePergunta;
    }

}

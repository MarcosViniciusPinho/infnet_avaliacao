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

@RunWith(SpringRunner.class)
@SpringBootTest
public class TemplateAvaliacaoTopicoPerguntaDTOUnitTest {

    @Test
    public void testToEntity(){
        TemplateAvaliacaoTopicoPergunta templateAvaliacaoTopicoPergunta = this.createTemplateAvaliacaoTopicoPergunta(3L);
        TemplateAvaliacaoTopicoPerguntaDTO templateAvaliacaoTopicoPerguntaDTO = this.createTemplateAvaliacaoTopicoPerguntaDTO(3L);
        Assert.assertNotNull(templateAvaliacaoTopicoPerguntaDTO.toEntity());
        Assert.assertEquals(templateAvaliacaoTopicoPergunta, templateAvaliacaoTopicoPerguntaDTO.toEntity());
    }

    @Test
    public void testToDto(){
        TemplateAvaliacaoTopicoPergunta templateAvaliacaoTopicoPergunta = this.createTemplateAvaliacaoTopicoPergunta(3L);
        TemplateAvaliacaoTopicoPerguntaDTO templateAvaliacaoTopicoPerguntaDTO = this.createTemplateAvaliacaoTopicoPerguntaDTO(3L);
        Assert.assertNotNull(TemplateAvaliacaoTopicoPerguntaDTO.toDto(templateAvaliacaoTopicoPergunta));
        Assert.assertEquals(templateAvaliacaoTopicoPerguntaDTO, TemplateAvaliacaoTopicoPerguntaDTO.toDto(templateAvaliacaoTopicoPergunta));
    }

    @Test(expected = NullParameterException.class)
    public void testToDtoFailedAlunoNull(){
        TemplateAvaliacaoTopicoPerguntaDTO.toDto(null);
    }

    /**
     * Métodos foram criados para auxiliar nos testes; ou seja; diminuir a codificação dos mesmos.
     */

    private TemplateAvaliacaoTopicoPerguntaDTO createTemplateAvaliacaoTopicoPerguntaDTO(Long id){
        TemplateAvaliacaoTopicoPerguntaDTO templateAvaliacaoTopicoPerguntaDTO = new TemplateAvaliacaoTopicoPerguntaDTO();
        templateAvaliacaoTopicoPerguntaDTO.setId(id);
        templateAvaliacaoTopicoPerguntaDTO.setTemplateAvaliacao(this.createTemplateAvaliacao(2L));
        templateAvaliacaoTopicoPerguntaDTO.setTemplateTopico(this.createTemplateTopico(3L));
        templateAvaliacaoTopicoPerguntaDTO.setTemplatePergunta(this.createTemplatePergunta(4L));
        return templateAvaliacaoTopicoPerguntaDTO;
    }

    private TemplateAvaliacaoTopicoPergunta createTemplateAvaliacaoTopicoPergunta(Long id){
        TemplateAvaliacaoTopicoPergunta templateAvaliacaoTopicoPergunta = new TemplateAvaliacaoTopicoPergunta();
        templateAvaliacaoTopicoPergunta.setId(id);
        templateAvaliacaoTopicoPergunta.setTemplateAvaliacao(this.createTemplateAvaliacao(2L));
        templateAvaliacaoTopicoPergunta.setTemplateTopico(this.createTemplateTopico(3L));
        templateAvaliacaoTopicoPergunta.setTemplatePergunta(this.createTemplatePergunta(4L));
        return templateAvaliacaoTopicoPergunta;
    }

    private TemplateAvaliacao createTemplateAvaliacao(Long id){
        TemplateAvaliacao templateAvaliacao = new TemplateAvaliacao();
        templateAvaliacao.setId(id);
        return templateAvaliacao;
    }

    private TemplateTopico createTemplateTopico(Long id){
        TemplateTopico templateTopico = new TemplateTopico();
        templateTopico.setId(id);
        return templateTopico;
    }

    private TemplatePergunta createTemplatePergunta(Long id){
        TemplatePergunta templatePergunta = new TemplatePergunta();
        templatePergunta.setId(id);
        return templatePergunta;
    }

}

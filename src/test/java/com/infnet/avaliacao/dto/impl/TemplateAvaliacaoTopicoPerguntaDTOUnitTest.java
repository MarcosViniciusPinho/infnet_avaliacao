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
    public void testToDtoFailedTemplateAvaliacaoTopicoPerguntaNull(){
        TemplateAvaliacaoTopicoPerguntaDTO.toDto(null);
    }

    @Test
    public void testConvertListEntityToListDto(){
        List<TemplateAvaliacaoTopicoPerguntaDTO> templateAvaliacaoTopicoPerguntaDTOList = new ArrayList<>();
        templateAvaliacaoTopicoPerguntaDTOList.add(this.createTemplateAvaliacaoTopicoPerguntaDTO(2L));
        templateAvaliacaoTopicoPerguntaDTOList.add(this.createTemplateAvaliacaoTopicoPerguntaDTO(6L));
        templateAvaliacaoTopicoPerguntaDTOList.add(this.createTemplateAvaliacaoTopicoPerguntaDTO(4L));

        List<TemplateAvaliacaoTopicoPergunta> templateAvaliacaoTopicoPerguntaList = new ArrayList<>();
        templateAvaliacaoTopicoPerguntaList.add(this.createTemplateAvaliacaoTopicoPergunta(2L));
        templateAvaliacaoTopicoPerguntaList.add(this.createTemplateAvaliacaoTopicoPergunta(6L));
        templateAvaliacaoTopicoPerguntaList.add(this.createTemplateAvaliacaoTopicoPergunta(4L));

        Assert.assertNotNull(TemplateAvaliacaoTopicoPerguntaDTO.convertListEntityToListDto(templateAvaliacaoTopicoPerguntaList));
        Assert.assertEquals(templateAvaliacaoTopicoPerguntaDTOList, TemplateAvaliacaoTopicoPerguntaDTO.convertListEntityToListDto(templateAvaliacaoTopicoPerguntaList));
    }

    @Test
    public void testConvertListEntityToListDtoEmpty(){
        Assert.assertNotNull(TemplateAvaliacaoTopicoPerguntaDTO.convertListEntityToListDto(new ArrayList<>()));
        Assert.assertEquals(new ArrayList<TemplateAvaliacaoTopicoPerguntaDTO>(), TemplateAvaliacaoTopicoPerguntaDTO.convertListEntityToListDto(new ArrayList<>()));
    }

    @Test
    public void testConvertListDtoToListEntity(){
        List<TemplateAvaliacaoTopicoPergunta> templateAvaliacaoTopicoPerguntaList = new ArrayList<>();
        templateAvaliacaoTopicoPerguntaList.add(this.createTemplateAvaliacaoTopicoPergunta(2L));
        templateAvaliacaoTopicoPerguntaList.add(this.createTemplateAvaliacaoTopicoPergunta(6L));
        templateAvaliacaoTopicoPerguntaList.add(this.createTemplateAvaliacaoTopicoPergunta(4L));

        List<TemplateAvaliacaoTopicoPerguntaDTO> templateAvaliacaoTopicoPerguntaDTOList = new ArrayList<>();
        templateAvaliacaoTopicoPerguntaDTOList.add(this.createTemplateAvaliacaoTopicoPerguntaDTO(2L));
        templateAvaliacaoTopicoPerguntaDTOList.add(this.createTemplateAvaliacaoTopicoPerguntaDTO(6L));
        templateAvaliacaoTopicoPerguntaDTOList.add(this.createTemplateAvaliacaoTopicoPerguntaDTO(4L));

        Assert.assertNotNull(TemplateAvaliacaoTopicoPerguntaDTO.convertListDtoToListEntity(templateAvaliacaoTopicoPerguntaDTOList));
        Assert.assertEquals(templateAvaliacaoTopicoPerguntaList, TemplateAvaliacaoTopicoPerguntaDTO.convertListDtoToListEntity(templateAvaliacaoTopicoPerguntaDTOList));
    }

    @Test
    public void testConvertListDtoToListEntityEmpty(){
        Assert.assertNotNull(TemplateAvaliacaoTopicoPerguntaDTO.convertListDtoToListEntity(new ArrayList<>()));
        Assert.assertEquals(new ArrayList<TemplateAvaliacaoTopicoPergunta>(), TemplateAvaliacaoTopicoPerguntaDTO.convertListDtoToListEntity(new ArrayList<>()));
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

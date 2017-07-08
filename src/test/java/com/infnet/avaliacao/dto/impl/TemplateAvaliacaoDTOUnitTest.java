package com.infnet.avaliacao.dto.impl;

import com.infnet.avaliacao.entity.TemplateAvaliacao;
import com.infnet.avaliacao.entity.TemplateTopico;
import com.infnet.avaliacao.exception.NullParameterException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TemplateAvaliacaoDTOUnitTest {

    @Mock
    private Pageable pageable;

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

    @Test
    public void testConvertListEntityToListDto(){
        List<TemplateAvaliacaoDTO> templateAvaliacaoDTOList = new ArrayList<>();
        templateAvaliacaoDTOList.add(this.createTemplateAvaliacaoDTO(2L));
        templateAvaliacaoDTOList.add(this.createTemplateAvaliacaoDTO(6L));
        templateAvaliacaoDTOList.add(this.createTemplateAvaliacaoDTO(4L));

        List<TemplateAvaliacao> templateAvaliacaoList = new ArrayList<>();
        templateAvaliacaoList.add(this.createTemplateAvaliacao(2L));
        templateAvaliacaoList.add(this.createTemplateAvaliacao(6L));
        templateAvaliacaoList.add(this.createTemplateAvaliacao(4L));

        Assert.assertNotNull(TemplateAvaliacaoDTO.convertListEntityToListDto(templateAvaliacaoList));
        Assert.assertEquals(templateAvaliacaoDTOList, TemplateAvaliacaoDTO.convertListEntityToListDto(templateAvaliacaoList));
    }

    @Test
    public void testConvertListEntityToListDtoEmpty(){
        Assert.assertNotNull(TemplateAvaliacaoDTO.convertListEntityToListDto(new ArrayList<>()));
        Assert.assertEquals(new ArrayList<TemplateAvaliacaoDTO>(), TemplateAvaliacaoDTO.convertListEntityToListDto(new ArrayList<>()));
    }

    @Test
    public void testConvertPageEntityToPageDto(){
        List<TemplateAvaliacaoDTO> templateAvaliacaoDTOList = new ArrayList<>();
        templateAvaliacaoDTOList.add(this.createTemplateAvaliacaoDTO(7L));
        templateAvaliacaoDTOList.add(this.createTemplateAvaliacaoDTO(2L));
        templateAvaliacaoDTOList.add(this.createTemplateAvaliacaoDTO(5L));

        Page<TemplateAvaliacaoDTO> pageDtoList = new PageImpl<>(templateAvaliacaoDTOList, pageable, templateAvaliacaoDTOList.size());

        List<TemplateAvaliacao> templateAvaliacaoList = new ArrayList<>();
        templateAvaliacaoList.add(this.createTemplateAvaliacao(7L));
        templateAvaliacaoList.add(this.createTemplateAvaliacao(2L));
        templateAvaliacaoList.add(this.createTemplateAvaliacao(5L));

        Page<TemplateAvaliacao> pageList = new PageImpl<>(templateAvaliacaoList, pageable, templateAvaliacaoList.size());

        Assert.assertNotNull(TemplateAvaliacaoDTO.convertPageEntityToPageDto(pageList, this.pageable));
        Assert.assertEquals(pageDtoList, TemplateAvaliacaoDTO.convertPageEntityToPageDto(pageList, this.pageable));
    }

    @Test
    public void testConvertPageEntityToPageDtoEmpty(){
        Page<TemplateAvaliacaoDTO> pageDtoList = new PageImpl<>(new ArrayList<>(), pageable, 0);
        Page<TemplateAvaliacao> pageList = new PageImpl<>(new ArrayList<>(), pageable, 0);
        Assert.assertNotNull(TemplateAvaliacaoDTO.convertPageEntityToPageDto(pageList, this.pageable));
        Assert.assertEquals(pageDtoList, TemplateAvaliacaoDTO.convertPageEntityToPageDto(pageList, this.pageable));
    }

    @Test
    public void testCarregarTopicosCadastradosParaFicarSelecionados(){
        TemplateAvaliacaoDTO templateAvaliacaoDTO = this.createTemplateAvaliacaoDTO(3L);
        templateAvaliacaoDTO.setTemplateTopicoDTOList(this.getTemplateTopicoDTOList());

        Assert.assertNotNull(templateAvaliacaoDTO.carregarTopicosCadastradosParaFicarSelecionados().getIdsTemplateTopicoSelecionados());
        Assert.assertEquals(this.getIdsTemplateTopicoSelecionados(), templateAvaliacaoDTO.carregarTopicosCadastradosParaFicarSelecionados().getIdsTemplateTopicoSelecionados());
    }

    @Test
    public void testCarregarTopicosCadastradosParaFicarSelecionadosEmpty(){
        TemplateAvaliacaoDTO templateAvaliacaoDTO = this.createTemplateAvaliacaoDTO(3L);

        Assert.assertNotNull(templateAvaliacaoDTO.carregarTopicosCadastradosParaFicarSelecionados().getIdsTemplateTopicoSelecionados());
        Assert.assertEquals(new ArrayList<>(), templateAvaliacaoDTO.carregarTopicosCadastradosParaFicarSelecionados().getIdsTemplateTopicoSelecionados());
    }

    /**
     * Métodos foram criados para auxiliar nos testes; ou seja; diminuir a codificação dos mesmos.
     */

    private List<Long> getIdsTemplateTopicoSelecionados(){
        List<Long> ids = new ArrayList<>();
        ids.add(2L);
        ids.add(1L);
        ids.add(3L);
        return ids;
    }

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

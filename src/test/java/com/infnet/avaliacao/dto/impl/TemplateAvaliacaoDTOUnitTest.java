package com.infnet.avaliacao.dto.impl;

import com.infnet.avaliacao.controller.wrapper.PerguntaAssociadaWrapper;
import com.infnet.avaliacao.entity.TemplateAvaliacao;
import com.infnet.avaliacao.entity.TemplatePergunta;
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

    @Test
    public void testIsVerificaTopicoAndAvaliacaoAndAtivo(){
        TemplateAvaliacaoDTO templateAvaliacaoDTO = this.createTemplateAvaliacaoDTO(3L);
        TemplateTopicoDTO templateTopicoDTO = this.createTemplateTopicoDTO(8L);
        TemplateAvaliacaoTopicoPerguntaDTO templateAvaliacaoTopicoPerguntaDTO = this.createTemplateAvaliacaoTopicoPerguntaDTO(1L, Boolean.TRUE, 1L);

        Assert.assertNotNull(templateAvaliacaoDTO.isVerificaTopicoAndAvaliacaoAndAtivo(templateTopicoDTO, templateAvaliacaoTopicoPerguntaDTO));
        Assert.assertEquals(Boolean.TRUE, templateAvaliacaoDTO.isVerificaTopicoAndAvaliacaoAndAtivo(templateTopicoDTO, templateAvaliacaoTopicoPerguntaDTO));
    }

    @Test
    public void testIsVerificaTopicoAndAvaliacaoAndAtivoFalse(){
        TemplateAvaliacaoDTO templateAvaliacaoDTO = this.createTemplateAvaliacaoDTO(3L);
        TemplateTopicoDTO templateTopicoDTO = this.createTemplateTopicoDTO(8L);
        TemplateAvaliacaoTopicoPerguntaDTO templateAvaliacaoTopicoPerguntaDTO = this.createTemplateAvaliacaoTopicoPerguntaDTO(1L, Boolean.FALSE, 1L);

        Assert.assertNotNull(templateAvaliacaoDTO.isVerificaTopicoAndAvaliacaoAndAtivo(templateTopicoDTO, templateAvaliacaoTopicoPerguntaDTO));
        Assert.assertEquals(Boolean.FALSE, templateAvaliacaoDTO.isVerificaTopicoAndAvaliacaoAndAtivo(templateTopicoDTO, templateAvaliacaoTopicoPerguntaDTO));
    }

    @Test
    public void testAddPerguntasAssociadas(){
        TemplateAvaliacaoDTO templateAvaliacaoDTO = this.createTemplateAvaliacaoDTO(3L);

        TemplateAvaliacaoTopicoPerguntaDTO templateAvaliacaoTopicoPerguntaDTO = this.createTemplateAvaliacaoTopicoPerguntaDTO(1L, Boolean.TRUE, 1L);

        List<TemplatePerguntaDTO> templatePerguntaDTOListEsperado = new ArrayList<>();
        templatePerguntaDTOListEsperado.add(this.createTemplatePerguntaDTO(1L));

        List<TemplatePerguntaDTO> templatePerguntaDTOList = new ArrayList<>();

        TemplateTopicoDTO templateTopicoDTO = this.createTemplateTopicoDTO(8L);

        templateAvaliacaoDTO.addPerguntasAssociadas(templateAvaliacaoTopicoPerguntaDTO, templatePerguntaDTOList, templateTopicoDTO);

        Assert.assertNotNull(templatePerguntaDTOList);
        Assert.assertEquals(templatePerguntaDTOListEsperado, templatePerguntaDTOList);
    }

    @Test
    public void testAddPerguntasAssociadasEmpty(){
        TemplateAvaliacaoDTO templateAvaliacaoDTO = this.createTemplateAvaliacaoDTO(3L);

        TemplateAvaliacaoTopicoPerguntaDTO templateAvaliacaoTopicoPerguntaDTO = this.createTemplateAvaliacaoTopicoPerguntaDTO(1L, Boolean.FALSE, 1L);

        List<TemplatePerguntaDTO> templatePerguntaDTOListEsperado = new ArrayList<>();

        List<TemplatePerguntaDTO> templatePerguntaDTOList = new ArrayList<>();

        TemplateTopicoDTO templateTopicoDTO = this.createTemplateTopicoDTO(8L);

        templateAvaliacaoDTO.addPerguntasAssociadas(templateAvaliacaoTopicoPerguntaDTO, templatePerguntaDTOList, templateTopicoDTO);

        Assert.assertNotNull(templatePerguntaDTOList);
        Assert.assertEquals(templatePerguntaDTOListEsperado, templatePerguntaDTOList);
    }

    @Test
    public void testPercorrerPerguntasAssociadas(){
        TemplateAvaliacaoDTO templateAvaliacaoDTO = this.createTemplateAvaliacaoDTO(3L);

        List<TemplatePerguntaDTO> templatePerguntaDTOListEsperado = new ArrayList<>();
        templatePerguntaDTOListEsperado.add(this.createTemplatePerguntaDTO(3L));
        templatePerguntaDTOListEsperado.add(this.createTemplatePerguntaDTO(5L));
        templatePerguntaDTOListEsperado.add(this.createTemplatePerguntaDTO(1L));

        List<TemplatePerguntaDTO> templatePerguntaDTOList = new ArrayList<>();

        List<TemplateAvaliacaoTopicoPerguntaDTO> templateAvaliacaoTopicoPerguntaDTOList = new ArrayList<>();
        templateAvaliacaoTopicoPerguntaDTOList.add(this.createTemplateAvaliacaoTopicoPerguntaDTO(3L, Boolean.TRUE, 3L));
        templateAvaliacaoTopicoPerguntaDTOList.add(this.createTemplateAvaliacaoTopicoPerguntaDTO(5L, Boolean.TRUE, 5L));
        templateAvaliacaoTopicoPerguntaDTOList.add(this.createTemplateAvaliacaoTopicoPerguntaDTO(1L, Boolean.TRUE, 1L));

        TemplateTopicoDTO templateTopicoDTO = this.createTemplateTopicoDTO(8L);
        templateTopicoDTO.setTemplateAvaliacaoTopicoPerguntaDTOList(templateAvaliacaoTopicoPerguntaDTOList);

        templateAvaliacaoDTO.percorrerPerguntasAssociadas(templateTopicoDTO, templatePerguntaDTOList);

        Assert.assertNotNull(templatePerguntaDTOList);
        Assert.assertEquals(templatePerguntaDTOListEsperado, templatePerguntaDTOList);
    }

    @Test
    public void testPercorrerPerguntasAssociadasEmpty(){
        TemplateAvaliacaoDTO templateAvaliacaoDTO = this.createTemplateAvaliacaoDTO(3L);

        List<TemplatePerguntaDTO> templatePerguntaDTOListEsperado = new ArrayList<>();

        List<TemplatePerguntaDTO> templatePerguntaDTOList = new ArrayList<>();

        TemplateTopicoDTO templateTopicoDTO = this.createTemplateTopicoDTO(8L);

        templateAvaliacaoDTO.percorrerPerguntasAssociadas(templateTopicoDTO, templatePerguntaDTOList);

        Assert.assertNotNull(templatePerguntaDTOList);
        Assert.assertEquals(templatePerguntaDTOListEsperado, templatePerguntaDTOList);
    }

    @Test
    public void testInit(){
        PerguntaAssociadaWrapper perguntaAssociadaWrapper = this.createPerguntaAssociadaWrapper();

        TemplateAvaliacaoDTO templateAvaliacaoDTO = this.createTemplateAvaliacaoDTO(3L);

        List<TemplatePerguntaDTO> templatePerguntaDTOList = new ArrayList<>();
        templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(3L));
        templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(5L));
        templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(1L));

        List<TemplateAvaliacaoTopicoPerguntaDTO> templateAvaliacaoTopicoPerguntaDTOList = new ArrayList<>();
        templateAvaliacaoTopicoPerguntaDTOList.add(this.createTemplateAvaliacaoTopicoPerguntaDTO(3L, Boolean.TRUE, 3L));
        templateAvaliacaoTopicoPerguntaDTOList.add(this.createTemplateAvaliacaoTopicoPerguntaDTO(5L, Boolean.TRUE, 5L));
        templateAvaliacaoTopicoPerguntaDTOList.add(this.createTemplateAvaliacaoTopicoPerguntaDTO(1L, Boolean.TRUE, 1L));

        TemplateTopicoDTO templateTopicoDTO = this.createTemplateTopicoDTO(8L);
        templateTopicoDTO.setTemplateAvaliacaoTopicoPerguntaDTOList(templateAvaliacaoTopicoPerguntaDTOList);

        perguntaAssociadaWrapper.setTemplatePerguntaDTOList(templatePerguntaDTOList);

        List<PerguntaAssociadaWrapper> perguntaAssociadaWrapperList = new ArrayList<>();

        List<PerguntaAssociadaWrapper> perguntaAssociadaWrapperListEsperado = new ArrayList<>();
        perguntaAssociadaWrapperListEsperado.add(perguntaAssociadaWrapper);

        templateAvaliacaoDTO.init(perguntaAssociadaWrapperList, templateTopicoDTO);

        Assert.assertNotNull(perguntaAssociadaWrapperList);
        Assert.assertEquals(perguntaAssociadaWrapperListEsperado, perguntaAssociadaWrapperList);
    }

    @Test
    public void testDetail(){
        PerguntaAssociadaWrapper perguntaAssociadaWrapper = this.createPerguntaAssociadaWrapper();

        List<TemplateAvaliacaoTopicoPerguntaDTO> templateAvaliacaoTopicoPerguntaDTOList = new ArrayList<>();
        templateAvaliacaoTopicoPerguntaDTOList.add(this.createTemplateAvaliacaoTopicoPerguntaDTO(3L, Boolean.TRUE, 3L));
        templateAvaliacaoTopicoPerguntaDTOList.add(this.createTemplateAvaliacaoTopicoPerguntaDTO(5L, Boolean.TRUE, 5L));
        templateAvaliacaoTopicoPerguntaDTOList.add(this.createTemplateAvaliacaoTopicoPerguntaDTO(1L, Boolean.TRUE, 1L));

        TemplateTopicoDTO templateTopicoDTO = this.createTemplateTopicoDTO(8L);
        templateTopicoDTO.setTemplateAvaliacaoTopicoPerguntaDTOList(templateAvaliacaoTopicoPerguntaDTOList);

        List<TemplateTopicoDTO> templateTopicoDTOList = new ArrayList<>();
        templateTopicoDTOList.add(templateTopicoDTO);

        TemplateAvaliacaoDTO templateAvaliacaoDTO = this.createTemplateAvaliacaoDTO(3L);
        templateAvaliacaoDTO.setTemplateTopicoDTOList(templateTopicoDTOList);

        List<TemplatePerguntaDTO> templatePerguntaDTOList = new ArrayList<>();
        templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(3L));
        templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(5L));
        templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(1L));

        perguntaAssociadaWrapper.setTemplatePerguntaDTOList(templatePerguntaDTOList);

        List<PerguntaAssociadaWrapper> perguntaAssociadaWrapperList = new ArrayList<>();

        List<PerguntaAssociadaWrapper> perguntaAssociadaWrapperListEsperado = new ArrayList<>();
        perguntaAssociadaWrapperListEsperado.add(perguntaAssociadaWrapper);

        templateAvaliacaoDTO.detail(perguntaAssociadaWrapperList);

        Assert.assertNotNull(perguntaAssociadaWrapperList);
        Assert.assertEquals(perguntaAssociadaWrapperListEsperado, perguntaAssociadaWrapperList);
    }

    /**
     * Métodos foram criados para auxiliar nos testes; ou seja; diminuir a codificação dos mesmos.
     */

    private PerguntaAssociadaWrapper createPerguntaAssociadaWrapper(){
        PerguntaAssociadaWrapper perguntaAssociadaWrapper = new PerguntaAssociadaWrapper();
        perguntaAssociadaWrapper.setTemplateTopicoDTO(this.createTemplateTopicoDTO(2L));
        return perguntaAssociadaWrapper;
    }

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

    private TemplateAvaliacaoTopicoPerguntaDTO createTemplateAvaliacaoTopicoPerguntaDTO(Long id, boolean ativo, Long idPergunta){
        TemplateAvaliacaoTopicoPerguntaDTO templateAvaliacaoTopicoPerguntaDTO = new TemplateAvaliacaoTopicoPerguntaDTO();
        templateAvaliacaoTopicoPerguntaDTO.setId(id);
        templateAvaliacaoTopicoPerguntaDTO.setTemplateAvaliacao(this.createTemplateAvaliacao(3L));
        templateAvaliacaoTopicoPerguntaDTO.setTemplateTopico(this.createTemplateTopico(8L));
        templateAvaliacaoTopicoPerguntaDTO.setTemplatePergunta(this.createTemplatePergunta(idPergunta));
        templateAvaliacaoTopicoPerguntaDTO.setAtivo(ativo);
        return templateAvaliacaoTopicoPerguntaDTO;
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

    private TemplatePergunta createTemplatePergunta(Long id){
        TemplatePergunta templatePergunta = new TemplatePergunta();
        templatePergunta.setId(id);
        return templatePergunta;
    }

    private TemplatePerguntaDTO createTemplatePerguntaDTO(Long id){
        TemplatePerguntaDTO templatePerguntaDTO = new TemplatePerguntaDTO();
        templatePerguntaDTO.setId(id);
        return templatePerguntaDTO;
    }

    private TemplateTopicoDTO createTemplateTopicoDTO(Long id){
        return TemplateTopicoDTO.toDto(this.createTemplateTopico(id));
    }

}

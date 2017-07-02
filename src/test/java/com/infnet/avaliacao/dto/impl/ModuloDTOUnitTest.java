package com.infnet.avaliacao.dto.impl;

import com.infnet.avaliacao.entity.Modulo;
import com.infnet.avaliacao.exception.NullParameterException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModuloDTOUnitTest {

    @Test
    public void testToEntity(){
        Modulo moduloEsperado = this.createModulo(3L);
        ModuloDTO moduloDTO = this.createModuloDTO(3L);
        Assert.assertNotNull(moduloDTO.toEntity());
        Assert.assertEquals(moduloEsperado, moduloDTO.toEntity());
    }

    @Test
    public void testToDto(){
        Modulo modulo = this.createModulo(3L);
        ModuloDTO moduloDTOEsperado = this.createModuloDTO(3L);
        Assert.assertNotNull(ModuloDTO.toDto(modulo));
        Assert.assertEquals(moduloDTOEsperado, ModuloDTO.toDto(modulo));
        Assert.assertEquals(moduloDTOEsperado.getTurmaList(), ModuloDTO.toDto(modulo).getTurmaList());
    }

    @Test(expected = NullParameterException.class)
    public void testToDtoModuloNull(){
        ModuloDTO.toDto(null);
    }

    /**
     * Métodos foram criados para auxiliar nos testes; ou seja; diminuir a codificação dos mesmos.
     */

    private ModuloDTO createModuloDTO(Long id){
        ModuloDTO moduloDTO = new ModuloDTO();
        moduloDTO.setId(id);
        moduloDTO.setDescricao("Descrição");
        return moduloDTO;
    }

    private Modulo createModulo(Long id){
        Modulo modulo = new Modulo();
        modulo.setId(id);
        modulo.setDescricao("Descrição");
        return modulo;
    }

}

package com.infnet.avaliacao.dto.impl;


import com.infnet.avaliacao.dto.DTO;
import com.infnet.avaliacao.dto.util.CriptografiaUtil;
import com.infnet.avaliacao.entity.Perfil;
import com.infnet.avaliacao.entity.Usuario;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa a tela de usuario.
 */
public class UsuarioDTO implements DTO<Usuario> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    private String login;

    private String senha;

    private Perfil perfil;


    /**
     * {@inheritDoc}
     */
    @Override
    public Usuario toEntity(){
        Usuario usuario = new Usuario();
        usuario.setId(this.getId());
        usuario.setNome(this.getNome());
        usuario.setLogin(this.getLogin());
        usuario.setSenha(this.getSenha());
        usuario.setPerfil(this.getPerfil());
        return usuario;
    }

    /**
     * Método que converte uma entidade para um dto.
     * @param usuario usuario
     * @return UsuarioDTO
     */
    public static UsuarioDTO toDto(Usuario usuario){
        ParameterExceptionUtil.validateObjectNull(usuario);
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setLogin(usuario.getLogin());
        usuarioDTO.setSenha(usuario.getSenha());
        usuarioDTO.setPerfil(usuario.getPerfil());
        return usuarioDTO;
    }

    /**
     * Método que converte uma lista de entidade para uma lista de dto.
     * @param entities entities
     * @return List<UsuarioDTO>
     */
    public static List<UsuarioDTO> convertListEntityToListDto(List<Usuario> entities){
        List<UsuarioDTO> lista = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(entities)){
            for(Usuario usuario : entities){
                lista.add(toDto(usuario));
            }
        }
        return lista;
    }

    /**
     * Método que converte uma lista de entidade para uma lista de dto.
     * @param entities entities
     * @param pageable pageable
     * @return Page<UsuarioDTO>
     */
    public static Page<UsuarioDTO> convertPageEntityToPageDto(Page<Usuario> entities, Pageable pageable){
        List<UsuarioDTO> lista = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(entities.getContent())){
            for(Usuario usuario : entities.getContent()){
                lista.add(toDto(usuario));
            }
        }
        return new PageImpl<>(lista, pageable, entities.getTotalElements());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = CriptografiaUtil.getSenhaCriptografada(senha, this.getId());
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
}

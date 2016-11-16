package com.infnet.avaliacao.dto;


import com.infnet.avaliacao.entity.Usuario;
import com.infnet.avaliacao.entity.domain.PerfilEnum;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe que representa a tela de usuario.
 */
public class UsuarioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    private String login;

    private String senha;

    private PerfilEnum perfil;


    /**
     * Método que converte um dto para uma entidade.
     * @return Usuario
     */
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
     * Método que verifica se os campos obrigatórios foram preenchidos na tela.
     * @return boolean
     */
    public boolean isCamposObrigatoriosVazios(){
        Optional<PerfilEnum> perfil = Optional.ofNullable(this.getPerfil());
        return StringUtils.isEmpty(this.getNome())
                || StringUtils.isEmpty(this.getLogin())
                || StringUtils.isEmpty(this.getSenha())
                || !perfil.isPresent();
    }

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
        this.senha = senha;
    }

    public PerfilEnum getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilEnum perfil) {
        this.perfil = perfil;
    }
}

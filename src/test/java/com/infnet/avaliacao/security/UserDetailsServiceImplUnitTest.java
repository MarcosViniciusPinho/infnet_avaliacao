package com.infnet.avaliacao.security;

import com.infnet.avaliacao.dto.impl.UsuarioDTO;
import com.infnet.avaliacao.entity.Perfil;
import com.infnet.avaliacao.entity.Role;
import com.infnet.avaliacao.entity.Usuario;
import com.infnet.avaliacao.exception.NullParameterException;
import com.infnet.avaliacao.repository.UsuarioRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDetailsServiceImplUnitTest {

	@InjectMocks
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Mock
	private UsuarioRepository usuarioRepository;

	@Test
	public void testLoadUserByUsername(){
		String login = "Marcos";
		Usuario usuario = this.createUsuario(4L);
		Mockito.when(this.usuarioRepository.findByLogin(login)).thenReturn(usuario);
		Assert.assertNotNull(this.userDetailsServiceImpl.loadUserByUsername(login));
		Assert.assertEquals(this.createUsuarioLogado(4L), this.userDetailsServiceImpl.loadUserByUsername(login));
	}

	@Test(expected = UsernameNotFoundException.class)
	public void testLoadUserByUsernameFailedUsuarioNaoEncontrado(){
		this.userDetailsServiceImpl.loadUserByUsername("Marcos");
	}

	@Test(expected = NullParameterException.class)
	public void testLoadUserByUsernameFailedLoginNull(){
		this.userDetailsServiceImpl.loadUserByUsername(null);
	}

	/**
	 * Métodos foram criados para auxiliar nos testes; ou seja; diminuir a codificação dos mesmos.
	 */

	private UsuarioLogado createUsuarioLogado(Long id){
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_MANTER_CADASTRO"));
		authorities.add(new SimpleGrantedAuthority("ROLE_CADASTRAR_USUARIO"));
		return new UsuarioLogado(this.createUsuarioDTO(id), authorities);
	}

	private UsuarioDTO createUsuarioDTO(Long id){
		return UsuarioDTO.toDto(this.createUsuario(id));
	}

	private Usuario createUsuario(Long id){
		List<Role> roleList = new ArrayList<>();
		roleList.add(this.createRole("MANTER_CADASTRO"));
		roleList.add(this.createRole("CADASTRAR_USUARIO"));
		Usuario usuario = new Usuario();
		usuario.setId(id);
		usuario.setPerfil(this.createPerfil(roleList));
		usuario.setLogin("Marcos");
		usuario.setSenha("12354");
		return usuario;
	}

	private Perfil createPerfil(List<Role> roleList){
		Perfil perfil = new Perfil();
		perfil.setNome("MANTER_CADASTRO");
		perfil.setRoleList(roleList);
		return perfil;
	}

	private Role createRole(String nome){
		Role role = new Role();
		role.setNome(nome);
		return role;
	}

}

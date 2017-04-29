package com.infnet.avaliacao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(this.passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/css/**")
                .antMatchers("/fonts/**")
                .antMatchers("/images/**")
                .antMatchers("/js/**");
    }

    private HttpSecurity verificarPermissao(HttpSecurity http) throws Exception {
        return http.authorizeRequests()
                .antMatchers("/cadastro/usuario/list").hasRole("LISTAR_USUARIO")
                .antMatchers("/cadastro/usuario/create").hasRole("CADASTRAR_USUARIO")
                .antMatchers("/cadastro/usuario/save").hasRole("SALVAR_USUARIO")
                .antMatchers("/cadastro/usuario/edit/{id}").hasRole("ALTERAR_USUARIO")
                .antMatchers("/cadastro/usuario/detail/{id}").hasRole("DETALHAR_USUARIO")
                .antMatchers("/cadastro/usuario/delete/{id}").hasRole("REMOVER_USUARIO")
                .antMatchers("/template/avaliacao/list").hasRole("LISTAR_TEMPLATE_AVALIACAO")
                .antMatchers("/template/avaliacao/detail/{id}").hasRole("DETALHAR_TEMPLATE_AVALIACAO")
                .antMatchers("/template/avaliacao/edit/{id}").hasRole("ASSOCIAR_TEMPLATE_TOPICO")
                .antMatchers("/template/avaliacao/save").hasRole("SALVAR_TEMPLATE_TOPICO")
                .antMatchers("/template/avaliacao/topico/edit/{id}/avaliacao/{idAvaliacao}").hasRole("ASSOCIAR_TEMPLATE_PERGUNTA")
                .antMatchers("/template/avaliacao/topico/save").hasRole("SALVAR_TEMPLATE_PERGUNTA")
                .antMatchers("/login").permitAll()
                .antMatchers("/").permitAll()
                .anyRequest().denyAll().and();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        this.verificarPermissao(http)
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .and()
                .exceptionHandling()
                    .accessDeniedPage("/acessoNegado");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.business.service.IUsuarioService;
import com.infnet.avaliacao.dto.impl.UsuarioDTO;
import com.infnet.avaliacao.entity.Usuario;
import com.infnet.avaliacao.entity.domain.PerfilEnum;
import com.infnet.avaliacao.exception.CampoObrigatorioException;
import com.infnet.avaliacao.exception.UniqueException;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import com.infnet.avaliacao.persistence.IUsuarioDAO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * {@inheritDoc}
 */
@Service
public class UsuarioService extends CrudService<UsuarioDTO, Usuario> implements IUsuarioService {

    @Resource
    private IUsuarioDAO usuarioDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(UsuarioDTO usuarioDTO) {
        this.validarCamposObrigatorios(usuarioDTO);
        this.validarLoginUnico(usuarioDTO);
    }

    private void validarCamposObrigatorios(UsuarioDTO usuarioDTO){
        Optional<PerfilEnum> perfil = Optional.ofNullable(usuarioDTO.getPerfil());
        if(StringUtils.isEmpty(usuarioDTO.getNome())
                || StringUtils.isEmpty(usuarioDTO.getLogin())
                || StringUtils.isEmpty(usuarioDTO.getSenha())
                || !perfil.isPresent()){
            throw new CampoObrigatorioException("usuario.mensagem.erro.obrigatorio");
        }
    }

    /**
     * Método que verifica se o login informado para o usuário corrente já existe.
     * @param usuarioDTO usuarioDTO
     */
    private void validarLoginUnico(UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioDao.findByLogin(usuarioDTO.getLogin());
        if(usuario != null && usuarioDTO.toEntity().equals(usuario)){
            throw new UniqueException("usuario.mensagem.erro.login.unico");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        ParameterExceptionUtil.validateObjectNull(id);
        this.usuarioDao.delete(id);
    }

}
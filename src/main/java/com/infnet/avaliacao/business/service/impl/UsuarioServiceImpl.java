package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.business.service.IUsuarioService;
import com.infnet.avaliacao.dto.impl.UsuarioDTO;
import com.infnet.avaliacao.entity.Usuario;
import com.infnet.avaliacao.exception.BusinessException;
import com.infnet.avaliacao.exception.UniqueException;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import com.infnet.avaliacao.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * {@inheritDoc}
 */
@Service
public class UsuarioServiceImpl extends CrudServiceImpl<UsuarioDTO, Usuario> implements IUsuarioService {

    @Resource
    private UsuarioRepository usuarioRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(UsuarioDTO usuarioDTO) {
        this.validarLoginUnico(usuarioDTO);
    }

    /**
     * Método que verifica se o login informado para o usuário corrente já existe.
     * @param usuarioDTO usuarioDTO
     */
    private void validarLoginUnico(UsuarioDTO usuarioDTO){
        Set<BusinessException> businessExceptionSet = new HashSet<>();
        Usuario usuario = usuarioRepository.findByLogin(usuarioDTO.getLogin());
        if(usuario != null && usuarioDTO.toEntity().equals(usuario)){
            businessExceptionSet.add(new UniqueException("usuario.mensagem.erro.login.unico"));
        }
        if(!businessExceptionSet.isEmpty()){
            throw new BusinessException(businessExceptionSet);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        ParameterExceptionUtil.validateObjectNull(id);
        this.usuarioRepository.delete(id);
    }

}
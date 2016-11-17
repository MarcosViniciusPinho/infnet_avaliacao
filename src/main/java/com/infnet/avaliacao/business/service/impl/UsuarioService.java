package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.business.service.IUsuarioService;
import com.infnet.avaliacao.dto.UsuarioDTO;
import com.infnet.avaliacao.entity.Usuario;
import com.infnet.avaliacao.entity.domain.PerfilEnum;
import com.infnet.avaliacao.exception.CampoObrigatorioException;
import com.infnet.avaliacao.exception.UniqueException;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import com.infnet.avaliacao.persistence.impl.IUsuarioDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {

    @Resource
    private IUsuarioDao usuarioDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(UsuarioDTO usuarioDTO) {
        ParameterExceptionUtil.validateObjectNull(usuarioDTO);
        this.validarCamposObrigatorios(usuarioDTO);
        this.validarLoginUnico(usuarioDTO);
    }

    private void validarCamposObrigatorios(UsuarioDTO usuarioDTO){
        Optional<PerfilEnum> perfil = Optional.ofNullable(usuarioDTO.getPerfil());
        if(StringUtils.isEmpty(usuarioDTO.getNome())
                || StringUtils.isEmpty(usuarioDTO.getLogin())
                || StringUtils.isEmpty(usuarioDTO.getSenha())
                || !perfil.isPresent()){
            throw new CampoObrigatorioException("Todos os campos destacados com * são obrigatórios");
        }
    }

    /**
     * Método que verifica se o login informado para o usuário corrente já existe.
     * @param usuarioDTO usuarioDTO
     */
    private void validarLoginUnico(UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioDao.findByLogin(usuarioDTO.getLogin());
        if(usuario != null && usuarioDTO.toEntity().equals(usuario)){
            throw new UniqueException("Já existe um usuário com o login informado.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(UsuarioDTO usuarioDTO) {
        this.validate(usuarioDTO);
        this.usuarioDao.save(usuarioDTO.toEntity());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UsuarioDTO findById(Long id) {
        ParameterExceptionUtil.validateObjectNull(id);
        return UsuarioDTO.toDto(this.usuarioDao.getOne(id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        ParameterExceptionUtil.validateObjectNull(id);
        this.usuarioDao.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UsuarioDTO> findAll() {
        return UsuarioDTO.convertListEntityToListDto(this.usuarioDao.findAll());
    }

}
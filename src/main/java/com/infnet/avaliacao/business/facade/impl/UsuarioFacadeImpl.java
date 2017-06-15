package com.infnet.avaliacao.business.facade.impl;

import com.infnet.avaliacao.business.facade.UsuarioFacade;
import com.infnet.avaliacao.business.service.PerfilService;
import com.infnet.avaliacao.business.service.UsuarioService;
import com.infnet.avaliacao.dto.impl.UsuarioDTO;
import com.infnet.avaliacao.entity.Perfil;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class UsuarioFacadeImpl implements UsuarioFacade {

    @Resource
    private UsuarioService usuarioService;

    @Resource
    private PerfilService perfilService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(UsuarioDTO usuarioDTO) {
        ParameterExceptionUtil.validateObjectNull(usuarioDTO);
        this.usuarioService.save(usuarioDTO);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UsuarioDTO findById(Long id) {
        ParameterExceptionUtil.validateObjectNull(id);
        return UsuarioDTO.toDto(this.usuarioService.findById(id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        ParameterExceptionUtil.validateObjectNull(id);
        this.usuarioService.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Perfil> findAllPerfil() {
        return this.perfilService.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UsuarioDTO> findAll() {
        return UsuarioDTO.convertListEntityToListDto(this.usuarioService.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<UsuarioDTO> findAllPaginated(Pageable pageable) {
        ParameterExceptionUtil.validateObjectNull(pageable);
        return UsuarioDTO.convertPageEntityToPageDto(this.usuarioService.findAllPaginated(pageable), pageable);
    }

}
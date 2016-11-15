package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.business.service.IUsuarioService;
import com.infnet.avaliacao.dto.UsuarioDTO;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import com.infnet.avaliacao.persistence.IUsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioDao usuarioDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(UsuarioDTO usuarioDTO) {
        ParameterExceptionUtil.validateObjectNull(usuarioDTO);
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
package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.business.service.ICrudService;
import com.infnet.avaliacao.dto.IDTO;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import com.infnet.avaliacao.persistence.ICrudDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Classe que representa o serviço generico
 */
public class CrudService<V extends IDTO<T>, T> implements ICrudService<V, T>{

    @Autowired
    private ICrudDAO<T> crudDAO;

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(V dto) {
        ParameterExceptionUtil.validateObjectNull(dto);
        this.validate(dto);
        this.crudDAO.saveAndFlush(dto.toEntity());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T findById(Long id) {
        ParameterExceptionUtil.validateObjectNull(id);
        return this.crudDAO.getOne(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> findAll() {
        return this.crudDAO.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(V dto) {
        /**
         * Vazio propositalmente.
         */
    }
}

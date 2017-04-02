package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.business.service.ICrudService;
import com.infnet.avaliacao.dto.IDTO;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import com.infnet.avaliacao.repository.ICrudRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Classe que representa o serviço generico
 */
public class CrudService<V extends IDTO<T>, T> implements ICrudService<V, T>{

    @Autowired
    private ICrudRepository<T> crudRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(V dto) {
        ParameterExceptionUtil.validateObjectNull(dto);
        this.validate(dto);
        this.crudRepository.saveAndFlush(dto.toEntity());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T findById(Long id) {
        ParameterExceptionUtil.validateObjectNull(id);
        return this.crudRepository.getOne(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> findAll() {
        return this.crudRepository.findAll();
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

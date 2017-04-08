package com.infnet.avaliacao.repository.impl;

import com.infnet.avaliacao.repository.PageRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class PageRepositoryImpl<T> implements PageRepository<T> {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public Page<T> findAll(Pageable pageable) {
        Criteria criteria = entityManager.unwrap(Session.class).createCriteria(this.getEntity());

        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;

        criteria.setFirstResult(primeiroRegistro);
        criteria.setMaxResults(totalRegistrosPorPagina);

        return new PageImpl<>(criteria.list(), pageable, totalRegistros());
    }

    private Long totalRegistros() {
        Criteria criteria = this.entityManager.unwrap(Session.class).createCriteria(this.getEntity());
        criteria.setProjection(Projections.rowCount());
        return (Long) criteria.uniqueResult();
    }

    public abstract Class<T> getEntity();

}

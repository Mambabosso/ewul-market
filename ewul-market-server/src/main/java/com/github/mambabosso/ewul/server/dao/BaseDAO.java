package com.github.mambabosso.ewul.server.dao;

import com.github.mambabosso.ewul.server.model.Persistable;
import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.hibernate.HibernateDeleteClause;
import com.querydsl.jpa.hibernate.HibernateQuery;
import com.querydsl.jpa.hibernate.HibernateUpdateClause;
import io.dropwizard.hibernate.AbstractDAO;
import lombok.NonNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;

public abstract class BaseDAO<T extends Persistable<PK>, PK extends Serializable> extends AbstractDAO<T> {

    protected BaseDAO(final SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    protected Session session() {
        return currentSession();
    }

    protected HibernateQuery<T> query(final long offset, final long limit) {
        HibernateQuery<T> query = new HibernateQuery<>(session());
        if (offset > 0) {
            query = query.offset(offset);
        }
        if (limit > 0) {
            query = query.limit(limit);
        }
        return query;
    }

    protected HibernateQuery<T> query() {
        return query(0, 0);
    }

    protected abstract PK insert(@NonNull final T entity);

    protected T getById(@NonNull final PK id) {
        return get(id);
    }

    protected HibernateUpdateClause update(@NonNull final EntityPath<T> entityPath) {
        return new HibernateUpdateClause(session(), entityPath);
    }

    protected HibernateDeleteClause delete(@NonNull final EntityPath<T> entityPath) {
        return new HibernateDeleteClause(session(), entityPath);
    }

}

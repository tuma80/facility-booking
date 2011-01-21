package com.iclub.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.ejb.HibernateEntityManager;
import org.springframework.stereotype.Repository;

import com.iclub.dao.GenericDao;

@Repository("genericDao")
public class GenericDaoImpl<T,PK extends Serializable> implements GenericDao<T, PK> {


    protected Class<T> entityClass;

    @PersistenceContext
    protected EntityManager entityManager;

	public T create(T t) {
        this.entityManager.persist(t);
        return t;
	}

	public T read(PK id,Class<T> c) {
		return (T)this.entityManager.find(c, id);
	}

	public T update(T t) {
		 return this.entityManager.merge(t);
	}

	public void delete(T t) {
       t = this.entityManager.merge(t);
       this.entityManager.remove(t);
	}
	
	public List<T> getAll(Class<T> c){
		return this.entityManager.createQuery("SELECT o FROM "+ c.getName() +" o").getResultList();
	}
	
	public List<T> getByFilter(T filter){
		Session session = ((HibernateEntityManager)entityManager.unwrap(HibernateEntityManager.class)).getSession();
		return session.createCriteria(filter.getClass()).add(Example.create(filter)).list();
	}

}

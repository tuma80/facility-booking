package com.iclub.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iclub.dao.GenericDao;
import com.iclub.dao.UserDao;
import com.iclub.member.vo.AuthenticateUser;

@Repository("userDao")
public class UserDaoImpl implements UserDao{
	
	@Autowired
	GenericDao<AuthenticateUser,Long> genericDao;
	
	@PersistenceContext
	EntityManager entityManager;

	public List<AuthenticateUser> userByName(String username) {
		String queryStr = "SELECT u FROM AuthenticateUser u WHERE u.username= ? ";
		return entityManager.createQuery(queryStr)
			   .setParameter(1,username).getResultList();
	}

	public AuthenticateUser create(AuthenticateUser t) {
		return genericDao.create(t);
	}

	public AuthenticateUser read(Long id, Class<AuthenticateUser> c) {
		return genericDao.read(id, c);
	}

	public AuthenticateUser update(AuthenticateUser t) {
		return genericDao.update(t);
	}

	public void delete(AuthenticateUser t) {
		genericDao.delete(t);
	}

	public List<AuthenticateUser> getAll(Class<AuthenticateUser> c) {
		return genericDao.getAll(c);
	}
}
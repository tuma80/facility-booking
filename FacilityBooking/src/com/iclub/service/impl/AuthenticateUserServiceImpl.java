package com.iclub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iclub.dao.GenericDao;
import com.iclub.member.vo.AuthenticateUser;
import com.iclub.service.AuthenticateUserService;

@Service("authenticateUserService")
public class AuthenticateUserServiceImpl implements AuthenticateUserService{
	@Autowired
	GenericDao<AuthenticateUser,Integer> authenticateUserDao;
	
	public List<AuthenticateUser> getAllAuthenticateUsers(){
		return authenticateUserDao.getAll(AuthenticateUser.class);
	}

	@Transactional
	public void createAuthenticateUser(AuthenticateUser c) {
		authenticateUserDao.create(c);
	}

	@Transactional
	public void update(AuthenticateUser p) {
		authenticateUserDao.update(p);
	}

	@Transactional
	public AuthenticateUser getAuthenticateUser(long id) {
		return authenticateUserDao.read((int)id, AuthenticateUser.class);
	}

	@Transactional
	public void delete(long id) {
		AuthenticateUser authenticateUser = new AuthenticateUser();
		authenticateUser.setId((int)id);
		authenticateUserDao.delete(authenticateUser);
	}
	

}

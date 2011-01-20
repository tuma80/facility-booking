package com.iclub.dao;

import java.util.List;

import com.iclub.member.vo.AuthenticateUser;

public interface UserDao {
	public List<AuthenticateUser> userByName(String username);
}
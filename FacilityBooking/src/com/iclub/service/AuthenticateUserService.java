package com.iclub.service;

import java.util.List;

import com.iclub.member.vo.AuthenticateUser;
import com.iclub.member.vo.BookedRoom;
import com.iclub.member.vo.Room;

public interface AuthenticateUserService {
	void createAuthenticateUser(AuthenticateUser c);
	void update(AuthenticateUser p);
	List<AuthenticateUser> getAllAuthenticateUsers();
	AuthenticateUser getAuthenticateUser(long id);
	void delete(long id);
}

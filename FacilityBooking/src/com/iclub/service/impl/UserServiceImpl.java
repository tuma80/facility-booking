package com.iclub.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iclub.dao.UserDao;
import com.iclub.member.vo.AuthenticateUser;
import com.iclub.member.vo.UserProfile;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserDetailsService{
	protected static Logger logger = Logger.getLogger("service");
	
	@Autowired
	private UserDao userDao;
	
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		UserDetails userdetail = null;
		try{
			List<AuthenticateUser> users = userDao.userByName(username);
			
			if(users.size() == 0){
				//throw new UsernameNotFoundException("Error in retrieving user");
				return null;
			}
			
			AuthenticateUser user = users.get(0);
			userdetail= new UserProfile(user.getUsername(), user.getPassword(),user.getId(), true, true, true, true, getAuthorities(user.getAccess()));
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in retrieving user");
			throw new UsernameNotFoundException("Error in retrieving user");
		}
		return userdetail;
	}
	
	public Collection<GrantedAuthority> getAuthorities(Integer access) {		
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);		
		logger.debug("Grant ROLE_USER to this user");
		authList.add(new GrantedAuthorityImpl("ROLE_USER"));
		
		if ( access.compareTo(1) == 0) {			
			logger.debug("Grant ROLE_ADMIN to this user");
			authList.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
		}
		return authList;
  }

}

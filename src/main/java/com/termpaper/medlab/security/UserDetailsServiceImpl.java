package com.termpaper.medlab.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.termpaper.medlab.dao.impl.UserDaoImpl;
import com.termpaper.medlab.model.UserAuth;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserDaoImpl userDao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserAuth userAuth = userDao.getUserAuth(email);
		if(userAuth == null)
			 throw new UsernameNotFoundException("No user with email " + email);
		return userAuth;
	}

}

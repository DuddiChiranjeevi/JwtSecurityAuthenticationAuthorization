package com.mindgraph.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.mindgraph.pojo.UserInfo;
import com.mindgraph.repository.UserInfoRepo;
@Component
public class UserInfoUserDetailsService implements UserDetailsService {
	@Autowired
	private UserInfoRepo userInfoRepo;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		 Optional<UserInfo> userInfo=   userInfoRepo.findByUserName(userName);
		return userInfo.map(UserInfoUserDetails ::new).orElseThrow(()->new UsernameNotFoundException("User Not found with this User name : "+userName));
		
	}

}

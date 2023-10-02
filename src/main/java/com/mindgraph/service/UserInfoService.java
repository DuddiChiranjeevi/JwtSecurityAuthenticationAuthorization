package com.mindgraph.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mindgraph.pojo.UserInfo;
import com.mindgraph.repository.UserInfoRepo;

@Service
public class UserInfoService {
	@Autowired
	private UserInfoRepo userInfoRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public String addUser(UserInfo userInfo) {
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		userInfoRepo.save(userInfo);
		return "User added successfully";
	}
	
	
}

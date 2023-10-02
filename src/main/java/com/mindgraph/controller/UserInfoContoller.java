package com.mindgraph.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindgraph.pojo.AuthRequest;
import com.mindgraph.pojo.UserInfo;
import com.mindgraph.service.JwtService;
import com.mindgraph.service.UserInfoService;

@RestController
@RequestMapping("/user")
public class UserInfoContoller {
	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserInfoService userInfoService;

	@PostMapping("/add")
	public String addUser(@RequestBody UserInfo userInfo) {
		return userInfoService.addUser(userInfo);
	}
	@PostMapping("/genrateToken")
	public String authenticationToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		if(authentication.isAuthenticated()) {
		return	jwtService.genrateToken(authRequest.getUserName());
		}else {
			throw new UsernameNotFoundException("Invalid User Request !!!");
		}
		 
	}
}



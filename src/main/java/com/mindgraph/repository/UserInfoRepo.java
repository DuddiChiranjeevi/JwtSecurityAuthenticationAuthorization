package com.mindgraph.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindgraph.pojo.UserInfo;

public interface UserInfoRepo  extends JpaRepository<UserInfo, Integer>{

	Optional<UserInfo> findByUserName(String userName);
	
}

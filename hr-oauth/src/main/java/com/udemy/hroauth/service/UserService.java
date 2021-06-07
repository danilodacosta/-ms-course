package com.udemy.hroauth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.hroauth.entities.User;
import com.udemy.hroauth.feignclients.UserFeignClients;

@Service
public class UserService {

	private static Logger logger = LoggerFactory.getLogger(UserService.class); 
	
	@Autowired
	private UserFeignClients userFeignClients;
	
	public User findByEmail(String email) {
		
		User user = this.userFeignClients.findByEmail(email).getBody();
		
		if(user == null) {
			logger.error("Email not found: "+ email);
			throw new IllegalArgumentException("Email not found.");
		}
		
		logger.info("Email found: "+ email);
		return user;
	}
}

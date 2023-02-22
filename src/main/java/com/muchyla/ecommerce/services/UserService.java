package com.muchyla.ecommerce.services;

import org.springframework.stereotype.Service;

import com.muchyla.ecommerce.repositories.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
}

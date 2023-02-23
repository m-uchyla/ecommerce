package com.muchyla.ecommerce.services;

import com.muchyla.ecommerce.models.User;

public interface IUserService {
	
	User getUserById(Long id);
	
	User getUserByEmail(String email);
	
	User addUser(String username, String email, String password);
	
	User updateUser(User user);

}

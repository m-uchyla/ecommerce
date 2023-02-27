package com.muchyla.ecommerce.services;

import java.util.List;

import com.muchyla.ecommerce.models.User;
import com.muchyla.ecommerce.security.UserPricipal;

public interface IUserService {
	
	List<User> getUsersList();
	
	User getUserById(Long id);
	
	User getUserByEmail(String email);
	
	User addUser(String username, String email, String password);
	
	User updateUser(User user);
	
	UserPricipal loadGoogleUser(String email, String username);
	
	User getUserByPrincipal (UserPricipal userPricipal);

}

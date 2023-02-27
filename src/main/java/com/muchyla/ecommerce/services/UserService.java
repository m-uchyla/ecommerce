package com.muchyla.ecommerce.services;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.muchyla.ecommerce.models.User;
import com.muchyla.ecommerce.repositories.UserRepository;
import com.muchyla.ecommerce.security.PasswordGenerator;
import com.muchyla.ecommerce.security.UserPricipal;

@Service
public class UserService implements IUserService {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	
	protected User returnOptionalUser(Optional<User> optUser) {
		return (optUser.isPresent()) ? optUser.get() : null;
	}
	
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User getUserById(Long id) {
		return returnOptionalUser(userRepository.findById(id));
	}

	@Override
	public User addUser(String username, String email, String password) {
		System.out.println("===== USER CREATION");
		return userRepository.save(new User(username, email, passwordEncoder.encode(password)));
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUserByEmail(String email) {
		return returnOptionalUser(userRepository.findByEmail(email));
	}

	@Override
	public UserPricipal loadGoogleUser(String email, String username) {
		User user = returnOptionalUser(userRepository.findByEmail(email));
		if(user == null) {
			user = addUser(username, email, (new PasswordGenerator.PasswordGeneratorBuilder().useDigits(true).useLower(true).useUpper(true).build().generate(30)));
		}
		return new UserPricipal(user);
	}

	@Override
	public List<User> getUsersList() {
		return userRepository.findAll();
	}

	@Override
	public User getUserByPrincipal(Object principal) {
		UserPricipal userPricipal = (UserPricipal) principal;
		return userPricipal.getUser();
	}

	@Override
	public User getLoggedUser() {
		return getUserByPrincipal(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
	}
	
}

package com.muchyla.ecommerce.services;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.muchyla.ecommerce.models.User;
import com.muchyla.ecommerce.repositories.UserRepository;

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
	
}

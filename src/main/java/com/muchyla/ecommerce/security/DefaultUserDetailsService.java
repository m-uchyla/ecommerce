package com.muchyla.ecommerce.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.muchyla.ecommerce.models.User;
import com.muchyla.ecommerce.repositories.UserRepository;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;
	
	@Autowired
	public DefaultUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByUsername(username);
		return new UserPricipal(user);
	}
	
	public User findByUsername(String username) {
		Optional<User> optUser = userRepository.findByUsername(username);
		return (optUser.isPresent()) ? optUser.get() :  null;
	}
	
	public void saveUser(User user) {
		userRepository.save(user);
	}

}

package com.muchyla.ecommerce.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.muchyla.ecommerce.models.Role;
import com.muchyla.ecommerce.models.User;
import com.muchyla.ecommerce.repositories.RoleRepository;
import com.muchyla.ecommerce.security.DefaultUserDetailsService;

@Profile("!test")
@Component
public class BootstrapData implements ApplicationRunner {
	
	private DefaultUserDetailsService userService;
	
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	public BootstrapData(DefaultUserDetailsService userService) {
		this.userService = userService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Role role = new Role("ADMIN");
		roleRepository.save(role);
		
		User user = new User("test", "test");
		user.setPassword(encoder.encode(user.getPassword()));
		userService.saveUser(user);
		
	}

}

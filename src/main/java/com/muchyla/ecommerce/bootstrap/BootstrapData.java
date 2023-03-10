package com.muchyla.ecommerce.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.muchyla.ecommerce.models.Role;
import com.muchyla.ecommerce.models.User;
import com.muchyla.ecommerce.repositories.ProductRepository;
import com.muchyla.ecommerce.repositories.RoleRepository;
import com.muchyla.ecommerce.security.DefaultUserDetailsService;
import com.muchyla.ecommerce.services.IProductService;
import com.muchyla.ecommerce.services.IUserService;

@Profile("!test")
@Component
public class BootstrapData implements ApplicationRunner {
	
	private IUserService userService;
	
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	IProductService productService;
	
	@Autowired
	public BootstrapData(IUserService userService) {
		this.userService = userService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Role role = new Role("ADMIN");
		roleRepository.save(role);
		
		Role role1 = new Role("USER");
		roleRepository.save(role1);
		
		User user = userService.addUser("test", "test@test.com", "test");
		user.setTwoFactorEnabled(true);
		user =  userService.updateUser(user);
		
		productService.createProduct("Test", "Description", "Test", 99.99, 10);
		
		System.out.println("\n"+user.toString());
	}

}

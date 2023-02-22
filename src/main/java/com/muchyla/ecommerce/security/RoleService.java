package com.muchyla.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muchyla.ecommerce.repositories.RoleRepository;

@Service
public class RoleService {

	private RoleRepository roleRepository;
	
	@Autowired
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
}

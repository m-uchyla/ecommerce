package com.muchyla.ecommerce.security;

import org.springframework.stereotype.Service;

import com.muchyla.ecommerce.repositories.RoleRepository;

@Service
public class RoleService {

	private RoleRepository roleRepository;
	

	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
}

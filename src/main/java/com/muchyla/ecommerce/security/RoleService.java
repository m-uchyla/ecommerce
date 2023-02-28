package com.muchyla.ecommerce.security;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.muchyla.ecommerce.models.Role;
import com.muchyla.ecommerce.repositories.RoleRepository;

@Service
public class RoleService {

	private RoleRepository roleRepository;
	

	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	protected Role getFromOptional(Optional<Role> optRole) {
		return (optRole.isPresent()) ? optRole.get() : null;
	}
	
	public Role getRoleByName(String name) {
		return getFromOptional(roleRepository.findByRoleName(name));
	}
}

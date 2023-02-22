package com.muchyla.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.muchyla.ecommerce.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}

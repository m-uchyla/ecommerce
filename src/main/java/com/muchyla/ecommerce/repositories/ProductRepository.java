package com.muchyla.ecommerce.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muchyla.ecommerce.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Optional<Product> findByName(String name);
	List<Product> findByCategory(String category);

}

package com.muchyla.ecommerce.services;

import java.util.List;

import com.muchyla.ecommerce.models.Product;

public interface IProductService {
	
	List<Product> getAllProducts();
	
	Product createProduct(String name, String description, String category, Double price, Integer stock);
	
	Product getProductByName(String name);
	
	Product getProductById(Long id);
	
	List<Product> getProductsByCategory(String category);

	Boolean removeOneFromStock(Long id);
}

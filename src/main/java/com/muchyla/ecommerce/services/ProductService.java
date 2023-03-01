package com.muchyla.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.muchyla.ecommerce.models.Product;
import com.muchyla.ecommerce.repositories.ProductRepository;

@Service
public class ProductService implements IProductService {

	private ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	protected Product getFromOptional(Optional<Product> prodOptional) {
		return (prodOptional.isPresent()) ? prodOptional.get() : null;
	}

	@Override
	public Product createProduct(String name, String description, String category, Double price, Integer stock) {
		return productRepository.save(new Product(name, description, category, price, stock));
	}

	@Override
	public Product getProductByName(String name) {
		return getFromOptional(productRepository.findByName(name));
	}

	@Override
	public Product getProductById(Long id) {
		return getFromOptional(productRepository.findById(id));
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		return productRepository.findByCategory(category);
	}

	@Override
	public Boolean removeOneFromStock(Long id) {
		Product product = getProductById(id);
		if(product.getStock() == 0)return false;
		product.setStock(product.getStock()-1);
		productRepository.save(product);
		return true;
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
}

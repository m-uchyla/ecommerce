package com.muchyla.ecommerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.muchyla.ecommerce.services.IProductService;

@Controller
public class ProductController {

	private IProductService productService;
	
	public ProductController(IProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/products")
	public String getProductsPage(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "productsPage";
	}
}

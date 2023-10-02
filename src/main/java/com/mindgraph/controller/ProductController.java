package com.mindgraph.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindgraph.pojo.Product;
import com.mindgraph.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;

	@GetMapping("/welcome")
	public String getMessage() {
		return productService.getMessage();

	}

	@GetMapping("/all")
	@PreAuthorize("hasAuthority('ROLE_DB-ADMIN')")
	public List<Product> findAll() {
		return productService.findAll();
	}

	@GetMapping("/find/{productId}")
	@PreAuthorize("hasRole('ROLE_DEVELOPER')")
	
	public Product getById(@PathVariable("productId") int productId) {
	 
		return productService.getById(productId);

	}
	@PostMapping("/save")
	public void save(@RequestBody Product product) {
		productService.save(product);
	}
}

package com.mindgraph.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mindgraph.exception.ProductNotFoundException;
import com.mindgraph.pojo.Product;
import com.mindgraph.repository.ProductRepo;
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepo productRepo;

	@Override
	public String getMessage() {
		
		return "Welcometo amazone mart";
	}

	@Override
	public void save(Product product) {
		productRepo.save(product);

	}

	@Override
	public List<Product> findAll() {
	
		return 	productRepo.findAll();
	}

	@Override
	@ExceptionHandler(ProductNotFoundException.class)
	public Product getById(int productId) {
	    Optional<Product> product = productRepo.findById(productId);
	    if (product.isPresent()) {
	        return product.get();
	    } else {
	        throw new ProductNotFoundException("Product with ID " + productId + " not found");
	    }
	}

}

package com.mindgraph.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mindgraph.pojo.Product;

@Service
public interface ProductService {
	String getMessage();
	void save(Product product);
	List<Product> findAll();
	Product getById(int productId);

}

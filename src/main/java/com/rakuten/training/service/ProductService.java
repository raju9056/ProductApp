package com.rakuten.training.service;

import java.util.List;

import com.rakuten.training.domain.Product;

public interface ProductService {
	void removeProduct(int id);
	List<Product> findAll();
	Product findById(int id);
  int addNewProduct(Product tobeAdded);
}

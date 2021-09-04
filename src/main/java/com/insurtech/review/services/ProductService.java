package com.insurtech.review.services;

import com.insurtech.review.domain.Product;

import java.util.Set;

public interface ProductService {

    Set<Product> getProducts();

    Product getProduct(Long id);

    void addProduct(Product product);
}

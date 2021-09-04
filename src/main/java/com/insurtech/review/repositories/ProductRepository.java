package com.insurtech.review.repositories;

import com.insurtech.review.domain.Comment;
import com.insurtech.review.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ProductRepository extends CrudRepository<Product,Long> {


}

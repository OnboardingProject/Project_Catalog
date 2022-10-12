package com.project.catalog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.catalog.model.Product;

/**
 * @author Amrutha Joseph
 * @description Repository class for product entity
 * @date 20 September 2022
 */

@Repository
public interface ProductRepository extends MongoRepository<Product, String>{

}

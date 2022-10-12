package com.project.catalog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.catalog.model.Category;

/**
 * @author Athira Rajan
 * @description Repository class for accessing category entities
 * @created_Date 22/09/2022
 */

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

}

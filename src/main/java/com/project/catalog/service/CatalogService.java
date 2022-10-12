package com.project.catalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.project.catalog.constants.CatalogConstant;
import com.project.catalog.exception.CatalogException;
import com.project.catalog.exception.ProductNotFoundException;
import com.project.catalog.model.Category;
import com.project.catalog.model.Product;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Amrutha Joseph
 * @description Service class for project catalog
 * @date 20 September 2022
 */

@Slf4j
@Service
public class CatalogService {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	/**
	 * @param productName
	 * @return List of products
	 * @throws ProductNotFoundException
	 * @description Fetch all products based on product name
	 */
	
	public List<Product> searchProductByName(String productName) {
		
		log.info("Inside CatalogService searchProductByName method");
		List<Product> products = mongoTemplate.find(Query.query(Criteria.where("productName").regex(productName, "i")
								 .and("isDeleted").is(false)), Product.class);
		
		if(!CollectionUtils.isEmpty(products)) {			
			log.info("Product found and return the product details from service");
			return products;
		}else {			
			log.warn("No products found with given name");
			throw new ProductNotFoundException(CatalogConstant.PRODUCT_NOT_FOUND);
		}
		
	}
	
	/**
	 * @param 
	 * @return category object.
	 * @throws CatalogException
	 * @description : fetch all categories of catalog.
	 */
	public List<Category> fetchCategory() {
		log.info("Entering into fetchCategory method");
		List<Category> categoryList = mongoTemplate.findAll(Category.class);

		if (categoryList.isEmpty()) {
			log.info("category not found");
			throw new CatalogException(CatalogConstant.CATEGORY_NOT_FOUND, HttpStatus.NOT_FOUND);
		} else {
			log.info("category fetched successfully, exiting categoryFetch method");
			return categoryList;
		}

	}
}

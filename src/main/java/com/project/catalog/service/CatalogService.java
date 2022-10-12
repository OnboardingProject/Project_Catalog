package com.project.catalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.project.catalog.constants.CatalogConstant;
import com.project.catalog.exception.CatalogException;
import com.project.catalog.exception.NoDataFoundException;
import com.project.catalog.exception.ProductNotFoundException;
import com.project.catalog.model.Category;
import com.project.catalog.model.Product;
import com.project.catalog.response.ProductDTO;

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
	
	/*
	 * This deleteProduct method will modify the isDeleted field in to true and
	 * returns the modified Product
	 * 
	 */
	
	public ProductDTO deleteProduct(String id) {
		log.info(CatalogConstant.PDT_CONST_DELT_STRT);
		Query query = new Query();
		query.addCriteria(Criteria.where("productId").is(id));
		Update update = new Update();
		update.set("isDeleted", true);
		Product pdt = mongoTemplate.findAndModify(query, update, Product.class);
		if (!ObjectUtils.isEmpty(pdt)) {
			log.info(CatalogConstant.PDT_CONST_DELT);
			ProductDTO productDTO = new ProductDTO(pdt.getProductId(), pdt.getProductName(), pdt.getContractSpend(),
					pdt.getStakeHolder(), pdt.getCategoryLevel(), pdt.getCategoryLevelDescription(),
					pdt.getProductDescription(), pdt.getCreatedBy(), pdt.getCreatedTime(), pdt.getLastUpdatedBy(),
					pdt.getLastUpdatedTime(), true);
			log.info(CatalogConstant.PDT_CONST_DELT_EXIT);
			return productDTO;
		} else
			log.info(CatalogConstant.PDT_CONST_NOT_DELT);
		throw new NoDataFoundException(CatalogConstant.PDT_CONST_EXP_DELT);
	}
}

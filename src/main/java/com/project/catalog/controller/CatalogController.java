package com.project.catalog.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.catalog.constants.CatalogConstant;
import com.project.catalog.model.Category;
import com.project.catalog.response.ResponsePayLoad;
import com.project.catalog.service.CatalogService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Amrutha Joseph
 * @description Controller class for project catalog
 * @date 21 September 2022
 */

@Slf4j
@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {
	
	@Autowired
	private CatalogService catalogService;
	
	/**
	 * @param productName
	 * @return List of products
	 * @description Fetch all products based on product name
	 */
	
	@GetMapping("/product/{productName}")
	public ResponseEntity<ResponsePayLoad> searchProduct(@PathVariable String productName){
		
		log.info("Inside CatalogController searchProduct method");
		
		List<Object> products = new ArrayList<>();
		products.addAll(catalogService.searchProductByName(productName));
		
		if(!CollectionUtils.isEmpty(products)) {
			log.info("Products found and return successfully from controller");
			return new ResponseEntity<ResponsePayLoad>(new ResponsePayLoad(products, CatalogConstant.PRODUCT_FOUND, ""), HttpStatus.FOUND);
		
		}else {
			log.info("Products searching failed in controller");
			return new ResponseEntity<ResponsePayLoad>(new ResponsePayLoad(null, "", CatalogConstant.PRODUCT_NOT_FOUND), HttpStatus.NOT_FOUND);
	
		}
	}
	

	/**
	 * @param 
	 * @return ResponseEntity<List<Category>>
	 * @description : Fetch category list of catalog project.
	 */
	@GetMapping("/fetchCategory")
	@Operation(summary = "Fetch category", description = "API related to fetch categories", tags = "Get category")
	public ResponseEntity<?> fetchCategory() {
		log.info("Entering into fetchCategory api");
		List<Category> category = catalogService.fetchCategory();
		if (CollectionUtils.isEmpty(category)) {
			log.info(CatalogConstant.CATEGORY_NOT_FOUND);
			return new ResponseEntity<List<Category>>(Collections.emptyList(), HttpStatus.NOT_FOUND);

		} else {
			log.info(CatalogConstant.CATEGORY_FETCHED);
			log.info("Exiting from fetchCategory api");
			return new ResponseEntity<List<Category>>(category, HttpStatus.OK);
		}
	}

}

package com.project.catalog.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.project.catalog.constants.CatalogConstant;
import com.project.catalog.exception.CatalogException;
import com.project.catalog.exception.ProductNotFoundException;
import com.project.catalog.model.Category;
import com.project.catalog.model.Product;

@SpringBootTest
@AutoConfigureMockMvc
public class CatalogServiceTest {
	
	@Mock
	private MongoTemplate mongoTemplate;
	
	@InjectMocks
	private CatalogService catalogService;
	
	List<Category> categories = new ArrayList<Category>();
	List<Product> products = new ArrayList<Product>();
	Product product1 = new Product();
	Product product2 = new Product();
	Product product3 = new Product();
	
	@BeforeEach
	public void setup() {
		
		List<String> categoryList = Arrays.asList("1-1-1","1-1-2");
	
		product1 = new Product("1", "Converse", 5, 3, categoryList, "Admin", LocalDateTime.now(), "Admin", LocalDateTime.now(), false);
		product2 = new Product("2", "Jira", 15, 10, categoryList, "Admin", LocalDateTime.now(), "Admin", LocalDateTime.now(), false);
		product3 = new Product("3", "Confluence", 6, 13, categoryList, "Admin", LocalDateTime.now(), "Admin", LocalDateTime.now(), false);
		
		products.add(product1);
		products.add(product2);
		products.add(product3);
		
		Category category1 = new Category("id1", 1, "internal", "u1212", LocalDateTime.now(), "u1212",
				LocalDateTime.now(), null);
		categories.add(category1);
	}
	
	@Test
	public void searchProductByNameSuccessTest() {
		
		List<Product> expectedProducts = new ArrayList<>();
		expectedProducts.add(product1);
		expectedProducts.add(product3);
		
		when(mongoTemplate.find(Query.query(Criteria.where("productName").regex("con", "i")
								 .and("isDeleted").is(false)), Product.class)).thenReturn(products);
		List<Product> actualProducts = catalogService.searchProductByName("con");
		
		assertEquals(expectedProducts.get(0).getProductName(), actualProducts.get(0).getProductName());
		assertEquals(expectedProducts.get(0).getCategoryLevel(), actualProducts.get(0).getCategoryLevel());
	}
	
	@Test
	public void searchProductByNameFailureTest() {
			
		products.clear();
		when(mongoTemplate.find(Query.query(Criteria.where("productName").regex("Zoom", "i")
								 .and("isDeleted").is(false)), Product.class)).thenReturn(products);
		
		ProductNotFoundException actualErrorMessage = assertThrows(ProductNotFoundException.class, () ->
		{
			catalogService.searchProductByName("Zoom");
		});
		
		assertEquals(CatalogConstant.PRODUCT_NOT_FOUND, actualErrorMessage.getMessage());
	}
	

	@Test
	public void fetchCategorySuccessTest() {
		
		when(mongoTemplate.findAll(Category.class)).thenReturn(categories);
		assertEquals(categories, catalogService.fetchCategory());
	}

	@Test
	public void fetchCategoryFailureTest() {
		categories.clear();
		when(mongoTemplate.findAll(Category.class)).thenReturn(categories);
		CatalogException actualErrormsg = assertThrows(CatalogException.class, () -> {
			catalogService.fetchCategory();
		});

		assertEquals(CatalogConstant.CATEGORY_NOT_FOUND, actualErrormsg.getErrorMessage());

	}

}

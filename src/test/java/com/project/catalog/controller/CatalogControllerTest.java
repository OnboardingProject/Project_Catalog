package com.project.catalog.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.project.catalog.model.Category;
import com.project.catalog.model.Product;
import com.project.catalog.response.ProductDTO;
import com.project.catalog.service.CatalogService;

@SpringBootTest
@AutoConfigureMockMvc
public class CatalogControllerTest {
	
	@Mock
	private CatalogService catalogService;
	
	@InjectMocks
	private CatalogController catalogController;
	
	@Autowired
	private MockMvc mockMvc;
	
	List<Category> categories = new ArrayList<Category>();
	List<Product> products = new ArrayList<Product>();
	Product product1 = new Product();
	Product product2 = new Product();
	Product product3 = new Product();
	
	@BeforeEach
	public void setup() {
		
		mockMvc = MockMvcBuilders.standaloneSetup(catalogController).build();
		
		List<String> categoryList = Arrays.asList("1-1-1","1-1-2");
		List<String> categoryDescList = Arrays.asList("Description1","Description2");
		
		product1 = new Product("1", "Converse", 5, 3, categoryList, categoryDescList, "Product Description", "Admin", LocalDateTime.now(), "Admin", LocalDateTime.now(), false);
		product2 = new Product("2", "Jira", 15, 10, categoryList, categoryDescList, "Product Description", "Admin", LocalDateTime.now(), "Admin", LocalDateTime.now(), false);
		product3 = new Product("3", "Confluence", 6, 13, categoryList, categoryDescList, "Product Description", "Admin", LocalDateTime.now(), "Admin", LocalDateTime.now(), false);
		
		products.add(product1);
		products.add(product2);
		products.add(product3);
		
		Category category1 = new Category("id1", 1, "internal", "u1212", LocalDateTime.now(), "u1212",
				LocalDateTime.now(), null);
		categories.add(category1);
		
	}
	
	public ProductDTO getProductTestData() {
		ProductDTO productDTO = new ProductDTO("123", "Teams", 500.0f, 4, Arrays.asList("1-2-3"),Arrays.asList("Description"), "Description of Product", "u45", null, "u45",
				null, false);
		return productDTO;
	}
	
	@Test
	public void searchProductSuccessTest() throws Exception {
		
		when(catalogService.searchProductByName("con")).thenReturn(products);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/catalog/searchproduct/con").contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isFound());
	}
	
	@Test
	public void searchProductFailureTest() throws Exception {
		
		products.clear();
		when(catalogService.searchProductByName("Zoom")).thenReturn(products);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/catalog/searchproduct/Zoom").contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	public void fetchAllCategoryTestSuccess() throws Exception {

		when(catalogService.fetchCategory()).thenReturn(categories);
		mockMvc.perform(get("/api/v1/catalog/fetchCategory").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void fetchAllCategoryTestFailure() throws Exception {

		categories.clear();
		when(catalogService.fetchCategory()).thenReturn(categories);
		mockMvc.perform(get("/api/v1/catalog/fetchCategory").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

	}

	@Test
	public void deleteProductTest() throws ParseException, Exception {
		ProductDTO productDTO = getProductTestData();
		productDTO.setDeleted(true);
		when(catalogService.deleteProduct(Mockito.anyString())).thenReturn(productDTO);
		mockMvc.perform(
				MockMvcRequestBuilders.delete("/api/v1/catalog/deleteproduct/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}


}

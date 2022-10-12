package com.project.catalog.constants;

public class CatalogConstant {
	
	public static final String PRODUCT_NOT_FOUND = "No Products found with given name";
	public static final String PRODUCT_FOUND = "Products fetched successfully";
	public static final String CATEGORY_NOT_FOUND = "Category is not found";
	public static final String CATEGORY_FETCHED = "Category details fetched";
	public static final String PDT_CONST_DELT_STRT= "Entering Delete product method of ProductService Impl";
	public static final String PDT_CONST_DELT = "In Service class ,product is deleted";
	public static final String PDT_CONST_DELT_EXIT="Exiting Delete product method in productService Impl";
	public static final String PDT_CONST_NOT_DELT = "In Service class ,product is not deleted since id is not found";
	public static final String PDT_CONST_EXP_DELT = "Give proper Id to delete";
	public static final String PDT_CONST_CONTR_STRT = "In Controller class,entering  delete API";
	public static final String PDT_CONST_CONTR_EXIT = "In Controller class,exiting  delete API";
	public static final String PDT_URL="api/v1/catalog";
	public static final String PDT_DLT_URL=	"/deleteproduct/{id}";
	public static final String PDT_SEARCH_URL=	"/searchproduct/{productName}";
	public static final String CATEGORY_FETCH_URL=	"/fetchCategory";

}

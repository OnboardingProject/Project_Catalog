package com.project.catalog.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Athira Rajan
 * @description Exception class for Catalog Project
 * @created_Date 22/09/2022
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CatalogException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String errorMessage;
	private HttpStatus httpStatus;

}

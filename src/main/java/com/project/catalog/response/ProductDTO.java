package com.project.catalog.response;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*
 * Dto class representing product
 */
@Document
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductDTO {
	
	private String productId;
	private String productName;
	private float contractSpend;
	private int stakeHolder;
	private List<String> categoryLevel;
	private List<String> categoryLevelDescription;
	private String productDescription;
	private String createdBy;
	private LocalDateTime createdTime;
	private String lastUpdatedBy;
	private LocalDateTime lastUpdatedTime;
	private boolean isDeleted;
}

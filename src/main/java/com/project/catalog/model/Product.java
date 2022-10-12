package com.project.catalog.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Amrutha Joseph
 * @description Entity class for product
 * @date 20 September 2022
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Product")
public class Product {

	@Id
	private String productId;
	private String productName;
	private float contractSpend;
	private int stakeHolder;
	private List<String> categoryLevel;
	private String createdBy;
	private LocalDateTime createdTime;
	private String lastUpdatedBy;
	private LocalDateTime lastUpdatedTime;
	private boolean isDeleted;
}

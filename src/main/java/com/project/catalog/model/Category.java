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
 * @author Athira Rajan
 * @description Entity class for storing categories
 * @created_Date 22/09/2022
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Category")
public class Category {
	@Id
	private String categoryId;
	private int levelId;
	private String levelName;
	private String createdBy;
	private LocalDateTime createdTime;
	private String lastUpdatedBy;
	private LocalDateTime lastUpdatedTime;
	private List<SubCategory> category;

}

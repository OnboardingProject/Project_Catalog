package com.project.catalog.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author Athira Rajan
 * @description Entity class for storing sub categories
 * @created_Date 22/09/2022
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubCategory {

	private int levelId;
	private String levelName;
	private List<SubCategory> categoryLevels;
	
}

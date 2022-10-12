package com.project.catalog.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Amrutha Joseph
 * @description Response details
 * @date 21 September 2022
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePayLoad {

	private List<Object> data;
	private String successMessage;
	private String errorMessage;
}

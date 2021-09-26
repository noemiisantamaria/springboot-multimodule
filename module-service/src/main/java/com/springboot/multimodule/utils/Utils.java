package com.springboot.multimodule.utils;

import org.springframework.data.domain.Sort;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Utils {
	
	public Sort.Direction getSortDirection(String direction) {
		if (direction.equals("asc")) {
			return Sort.Direction.ASC;
		} else if (direction.equals("desc")) {
			return Sort.Direction.DESC;
		}
		return Sort.Direction.ASC;
	}
	
}

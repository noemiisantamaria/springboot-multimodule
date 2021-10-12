package com.springboot.multimodule.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Utils {
	
	public Pageable getPageable(Integer page, Integer rows, String sortValue) {
		String[] sortArray = sortValue.split(":");
		Sort sort = Sort.by(new Order(Utils.getSortDirection(sortArray[1]), sortArray[0]));
		return PageRequest.of(page, rows, sort);
	}
	
	public Sort.Direction getSortDirection(String direction) {
		return direction.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
	}
	
}

package com.springboot.multimodule.services;

import org.springframework.data.domain.Page;

import com.springboot.multimodule.entities.Comic;

public interface ComicService {
	
	Page<Comic> fetchAllComics(Integer page, Integer rows, String sort);

}

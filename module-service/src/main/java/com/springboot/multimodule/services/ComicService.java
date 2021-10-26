package com.springboot.multimodule.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.multimodule.entities.Comic;

public interface ComicService {
	
	Page<Comic> fetchAllComics(Pageable pageable);

}

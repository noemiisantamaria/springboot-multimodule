package com.springboot.multimodule.services;

import org.springframework.data.domain.Page;

import com.springboot.multimodule.entities.Superhero;

public interface SuperheroService {
	
	Page<Superhero> fetchAllSuperheroes(Integer page, Integer rows, String sort);

}

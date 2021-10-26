package com.springboot.multimodule.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.multimodule.entities.Superhero;

public interface SuperheroService {

	Superhero addSuperhero(Superhero superhero);
	
	void deleteSuperhero(Long idSuperhero);

	Page<Superhero> fetchAllSuperheroes(Pageable pageable);
	
	Superhero getSuperhero(Long idSuperhero);
	
	Superhero updateSuperhero(Superhero superhero);

}

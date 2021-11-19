package com.springboot.multimodule.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.multimodule.entities.Superhero;

public interface SuperheroService {

	Superhero addSuperhero(Superhero superhero);
	
	void deleteSuperhero(Long idSuperhero);
	
	Page<Superhero> fetchAllSuperheroes(String search, List<Integer> comics, Pageable pageable);
	
	Superhero getSuperhero(Long idSuperhero);
	
	Superhero updateSuperhero(Superhero superhero);

}

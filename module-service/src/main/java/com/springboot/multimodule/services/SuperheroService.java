package com.springboot.multimodule.services;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.springboot.multimodule.entities.Superhero;

public interface SuperheroService {

	Superhero addSuperhero(Superhero superhero);

	Page<Superhero> fetchAllSuperheroes(Integer page, Integer rows, String sort);
	
	Optional<Superhero> getSuperhero(Long idSuperhero);

}

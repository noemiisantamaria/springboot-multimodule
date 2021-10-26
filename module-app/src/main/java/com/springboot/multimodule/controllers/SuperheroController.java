package com.springboot.multimodule.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.multimodule.entities.Superhero;
import com.springboot.multimodule.services.SuperheroService;
import com.springboot.multimodule.utils.JsonResponseBody;
import com.springboot.multimodule.utils.JsonResponseEntity;

@RestController
public class SuperheroController {

	private static final Logger log = LoggerFactory.getLogger(SuperheroController.class);
	private static final Marker info = MarkerFactory.getMarker("INFO");
	private static final String CONTROLLER = "SuperheroController ";

	@Autowired
	SuperheroService superheroService;

	@PostMapping(path = "/superhero/add")
	public ResponseEntity<JsonResponseBody> addSuperhero(@Valid @RequestBody Superhero hero) {
		log.info(info, CONTROLLER + "addSuperhero - Superhero: {}", hero);
		Superhero superhero = superheroService.addSuperhero(hero);
		JsonResponseEntity entity = new JsonResponseEntity(Optional.ofNullable(superhero), HttpStatus.CREATED);
		return ResponseEntity.status(entity.getStatus()).body(entity.getBody());
	}
	
	@DeleteMapping(path = "/superhero/{idSuperhero}")
	public ResponseEntity<JsonResponseBody> deleteSuperhero(HttpServletRequest request,
			@PathVariable(value = "idSuperhero") Long idSuperhero) {
		log.info(info, CONTROLLER + "deleteSuperhero - idSuperhero: {}", idSuperhero);
		superheroService.deleteSuperhero(idSuperhero);
		JsonResponseEntity entity = new JsonResponseEntity(Optional.empty(), HttpStatus.OK);
		return ResponseEntity.status(entity.getStatus()).body(entity.getBody());
	}

	@GetMapping(path = "/superheroes")
	public ResponseEntity<JsonResponseBody> fetchAllSuperheroes(Pageable pageable) {
		log.info(info, CONTROLLER + "fetchAllSuperheroes");
		Page<Superhero> list = superheroService.fetchAllSuperheroes(pageable);
		JsonResponseEntity entity = new JsonResponseEntity(pageable.getPageNumber(), list);
		return ResponseEntity.status(entity.getStatus()).body(entity.getBody());
	}

	@GetMapping(path = "/superhero/{idSuperhero}")
	public ResponseEntity<JsonResponseBody> getSuperhero(HttpServletRequest request,
			@PathVariable(value = "idSuperhero") Long idSuperhero) {
		log.info(info, CONTROLLER + "getSuperhero - idSuperhero: {}", idSuperhero);
		Superhero superhero = superheroService.getSuperhero(idSuperhero);
		JsonResponseEntity entity = new JsonResponseEntity(Optional.ofNullable(superhero), HttpStatus.OK);
		return ResponseEntity.status(entity.getStatus()).body(entity.getBody());
	}
	
	@PutMapping(path = "/superhero/update")
	public ResponseEntity<JsonResponseBody> updateSuperhero(@Valid @RequestBody Superhero hero) {
		log.info(info, CONTROLLER + "updateSuperhero - Superhero: {}", hero);
		Superhero superhero = superheroService.updateSuperhero(hero);
		JsonResponseEntity entity = new JsonResponseEntity(Optional.ofNullable(superhero), HttpStatus.OK);
		return ResponseEntity.status(entity.getStatus()).body(entity.getBody());
	}

}

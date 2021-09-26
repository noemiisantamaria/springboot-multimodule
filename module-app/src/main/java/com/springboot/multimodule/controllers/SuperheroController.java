package com.springboot.multimodule.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.multimodule.entities.Superhero;
import com.springboot.multimodule.services.SuperheroService;
import com.springboot.multimodule.utils.JsonResponseBody;
import com.springboot.multimodule.utils.JsonResponseEntity;

@RestController
public class SuperheroController {
	
	private static final Logger log = LoggerFactory.getLogger(SuperheroController.class);
	private static final Marker info = MarkerFactory.getMarker("INFO");
	
	@Autowired
	SuperheroService superheroService;
	
	@GetMapping(path = "/superheroes")
	public ResponseEntity<JsonResponseBody> fetchAllSuperheroes(HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "rows", defaultValue = "10") Integer rows,
			@RequestParam(value = "sort", defaultValue = "name:asc") String sort) {
		log.info(info, "[SuperheroController] fetchAllSuperheroes");
		Page<Superhero> list = superheroService.fetchAllSuperheroes(page, rows, sort);
		JsonResponseEntity entity = new JsonResponseEntity(page, list);
		return ResponseEntity.status(entity.getStatus()).body(entity.getBody());
	}

}

package com.springboot.multimodule.controllers;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.multimodule.common.JsonResponseBody;
import com.springboot.multimodule.common.JsonResponseEntity;
import com.springboot.multimodule.dtos.SuperheroDto;
import com.springboot.multimodule.entities.Superhero;
import com.springboot.multimodule.services.SuperheroService;

@RestController
public class SuperheroController {

	private static final Logger log = LoggerFactory.getLogger(SuperheroController.class);
	private static final Marker info = MarkerFactory.getMarker("INFO");
	private static final String CONTROLLER = "SuperheroController ";
	
	@Autowired
	ModelMapper modelMapper;
	
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
	public ResponseEntity<JsonResponseBody> fetchAllSuperheroes(
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "comics", required = false) List<Integer> comics, Pageable pageable) {
		log.info(info, CONTROLLER + "fetchAllSuperheroes - search: {}, comics: {}", search, comics);
		Page<SuperheroDto> list = convertListToDto(superheroService.fetchAllSuperheroes(search, comics, pageable));
		JsonResponseEntity entity = new JsonResponseEntity(pageable.getPageNumber(), list);
		return ResponseEntity.status(entity.getStatus()).body(entity.getBody());
	}

	@GetMapping(path = "/superhero/{idSuperhero}")
	public ResponseEntity<JsonResponseBody> getSuperhero(HttpServletRequest request,
			@PathVariable(value = "idSuperhero") Long idSuperhero) {
		log.info(info, CONTROLLER + "getSuperhero - idSuperhero: {}", idSuperhero);
		SuperheroDto superhero = convertToDto(superheroService.getSuperhero(idSuperhero));
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
	
	private SuperheroDto convertToDto(Superhero superhero) {
		SuperheroDto superheroDto = modelMapper.map(superhero, SuperheroDto.class);
		return superheroDto;
	}
	
	private Page<SuperheroDto> convertListToDto(Page<Superhero> list) {
		Page<SuperheroDto> dtoPage = list.map(new Function<Superhero, SuperheroDto>() {
			@Override
			public SuperheroDto apply(Superhero superhero) {
				return modelMapper.map(superhero, SuperheroDto.class);
			}
		});
		return dtoPage;
	}

}

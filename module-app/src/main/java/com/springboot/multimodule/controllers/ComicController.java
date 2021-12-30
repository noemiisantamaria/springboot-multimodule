package com.springboot.multimodule.controllers;

import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.multimodule.common.JsonResponseBody;
import com.springboot.multimodule.common.JsonResponseEntity;
import com.springboot.multimodule.dtos.ComicDto;
import com.springboot.multimodule.entities.Comic;
import com.springboot.multimodule.services.ComicService;

@RestController
public class ComicController {
	
	private static final Logger log = LoggerFactory.getLogger(ComicController.class);
	private static final Marker info = MarkerFactory.getMarker("INFO");
	
	@Autowired
	ComicService comicService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping(path = "/comics")
	public ResponseEntity<JsonResponseBody> fetchAllComics(Pageable pageable) {
		log.info(info, "[ComicController] fetchAllComics");
		Page<ComicDto> list = convertListToDto(comicService.fetchAllComics(pageable));
		JsonResponseEntity entity = new JsonResponseEntity(pageable.getPageNumber(), list);
		return ResponseEntity.status(entity.getStatus()).body(entity.getBody());
	}
	
	private Page<ComicDto> convertListToDto(Page<Comic> list) {
		Page<ComicDto> dtoPage = list.map(new Function<Comic, ComicDto>() {
			@Override
			public ComicDto apply(Comic comic) {
				return modelMapper.map(comic, ComicDto.class);
			}
		});
		return dtoPage;
	}

}

package com.springboot.multimodule.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.springboot.multimodule.daos.SuperheroDao;
import com.springboot.multimodule.entities.Superhero;
import com.springboot.multimodule.errors.SuperheroNotFoundException;
import com.springboot.multimodule.specification.GenericSpecification;
import com.springboot.multimodule.specification.GenericSpecificationBuilder;
import com.springboot.multimodule.utils.CriteriaParser;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SuperheroServiceImpl implements SuperheroService {
	
	private static final Logger log = LoggerFactory.getLogger(SuperheroServiceImpl.class);
	private static final Marker info = MarkerFactory.getMarker("INFO");
	private static final String SERVICE = "[SuperheroServiceImpl] ";
	
	@Autowired
	SuperheroDao sdao;

	@Override
	public Superhero addSuperhero(Superhero superhero) {
		return sdao.save(superhero);
	}
	
	@Override
	public void deleteSuperhero(Long idSuperhero) {
		log.info(info, SERVICE + "deleteSuperhero - idSuperhero: {}", idSuperhero);
		sdao.findById(idSuperhero).orElseThrow(() -> new SuperheroNotFoundException(idSuperhero));
		sdao.deleteById(idSuperhero);
	}
	
	@Override
	public Page<Superhero> fetchAllSuperheroes(String search, Pageable pageable) {
		log.info(info, SERVICE + "fetchAllSuperheroes - search");
		Specification<Superhero> specs = resolveSpecificationFromSearch(search);
		return specs == null ? sdao.findAll(pageable) : sdao.findAll(specs, pageable);
	}
	
	private Specification<Superhero> resolveSpecificationFromSearch(String searchParameters) {
		if (searchParameters != null && !searchParameters.equals("")) {
			CriteriaParser parser = new CriteriaParser();
			GenericSpecificationBuilder<Superhero> specBuilder = new GenericSpecificationBuilder<>();
			return specBuilder.build(parser.parse(searchParameters), GenericSpecification::new);			
		}
		return null;
    }

	@Override
	public Superhero getSuperhero(Long idSuperhero) {
		log.info(info, SERVICE + "getSuperhero - idSuperhero: {}", idSuperhero);
		Superhero superhero = sdao.findById(idSuperhero).orElseThrow(() -> new SuperheroNotFoundException(idSuperhero));
		return superhero;
	}

	@Override
	public Superhero updateSuperhero(Superhero superhero) {
		log.info(info, SERVICE + "updateSuperhero - superhero: {}", superhero);
		sdao.findById(superhero.getId()).orElseThrow(() -> new SuperheroNotFoundException(superhero.getId()));
		return sdao.save(superhero);
	}
	
}

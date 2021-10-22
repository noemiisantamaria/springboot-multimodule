package com.springboot.multimodule.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.multimodule.daos.SuperheroDao;
import com.springboot.multimodule.entities.Superhero;
import com.springboot.multimodule.error.SuperheroNotFoundException;
import com.springboot.multimodule.utils.Utils;

@Service @Transactional
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
	public Page<Superhero> fetchAllSuperheroes(Integer page, Integer rows, String sort) {
		log.info(info, SERVICE + "fetchAllSuperheroes");
		Pageable pageable = Utils.getPageable(page, rows, sort);
		return sdao.findAll(pageable);
	}

	@Override
	public Optional<Superhero> getSuperhero(Long idSuperhero) {
		Optional<Superhero> superhero = Optional.ofNullable(sdao.findById(idSuperhero).orElseThrow(() -> new SuperheroNotFoundException()));
		return superhero;
	}

	@Override
	public Superhero updateSuperhero(Superhero superhero) {
//		return sdao.findById(superhero.getId()).map(hero -> 
//			sdao.save(superhero)
//		).orElseThrow(() -> new SuperheroNotFoundException());
		sdao.findById(superhero.getId()).orElseThrow(() -> new SuperheroNotFoundException());
		return sdao.save(superhero);
	}

}

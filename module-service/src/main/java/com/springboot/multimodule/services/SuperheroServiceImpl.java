package com.springboot.multimodule.services;

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

@Service @Transactional
public class SuperheroServiceImpl implements SuperheroService {
	
	private static final Logger log = LoggerFactory.getLogger(SuperheroServiceImpl.class);
	private static final Marker info = MarkerFactory.getMarker("INFO");
	private static final String SERVICE = "[SuperheroServiceImpl] ";
	
	@Autowired
	SuperheroDao sdao;
	
	public SuperheroServiceImpl(SuperheroDao sdao) {
        this.sdao = sdao;
    }

	@Override
	public Superhero addSuperhero(Superhero superhero) {
		return sdao.save(superhero);
	}
	
	@Override
	public void deleteSuperhero(Long idSuperhero) {
		log.info(info, SERVICE + "deleteSuperhero - idSuperhero: {}", idSuperhero);
		sdao.findById(idSuperhero).orElseThrow(() -> new SuperheroNotFoundException());
		sdao.deleteById(idSuperhero);
	}
	
	@Override
	public Page<Superhero> fetchAllSuperheroes(Pageable pageable) {
		log.info(info, SERVICE + "fetchAllSuperheroes");
		return sdao.findAll(pageable);
	}

	@Override
	public Superhero getSuperhero(Long idSuperhero) {
		log.info(info, SERVICE + "getSuperhero - idSuperhero: {}", idSuperhero);
		Superhero superhero = sdao.findById(idSuperhero).orElseThrow(() -> new SuperheroNotFoundException());
		return superhero;
	}

	@Override
	public Superhero updateSuperhero(Superhero superhero) {
		log.info(info, SERVICE + "updateSuperhero - superhero: {}", superhero);
		sdao.findById(superhero.getId()).orElseThrow(() -> new SuperheroNotFoundException());
		return sdao.save(superhero);
	}
	
}

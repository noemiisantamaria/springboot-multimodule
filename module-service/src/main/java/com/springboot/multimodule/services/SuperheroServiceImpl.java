package com.springboot.multimodule.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.multimodule.daos.SuperheroDao;
import com.springboot.multimodule.entities.Superhero;
import com.springboot.multimodule.utils.Utils;

@Service @Transactional
public class SuperheroServiceImpl implements SuperheroService {
	
	private static final Logger log = LoggerFactory.getLogger(SuperheroServiceImpl.class);
	private static final Marker info = MarkerFactory.getMarker("INFO");
	private static final String SERVICE = "[SuperheroServiceImpl] ";
	
	@Autowired
	SuperheroDao sdao;

	@Override
	public Page<Superhero> fetchAllSuperheroes(Integer page, Integer rows, String sortValue) {
		log.info(info, SERVICE + "fetchAllSuperheroes");
		String[] sortArray = sortValue.split(":");
		Sort sort = Sort.by(new Order(Utils.getSortDirection(sortArray[1]), sortArray[0]));
		Pageable pageable = PageRequest.of(page, rows, sort);
		return sdao.findAll(pageable);
	}

}

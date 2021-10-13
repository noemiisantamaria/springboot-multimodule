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

import com.springboot.multimodule.daos.ComicDao;
import com.springboot.multimodule.entities.Comic;
import com.springboot.multimodule.utils.Utils;

@Service @Transactional
public class ComicsServiceImpl implements ComicService {
	
	private static final Logger log = LoggerFactory.getLogger(ComicsServiceImpl.class);
	private static final Marker info = MarkerFactory.getMarker("INFO");
	private static final String SERVICE = "[ComicsServiceImpl] ";
	
	@Autowired
	ComicDao cdao;

	@Override
	public Page<Comic> fetchAllComics(Integer page, Integer rows, String sort) {
		log.info(info, SERVICE + "fetchAllComics");
		Pageable pageable = Utils.getPageable(page, rows, sort);
		return cdao.findAll(pageable);
	}

}

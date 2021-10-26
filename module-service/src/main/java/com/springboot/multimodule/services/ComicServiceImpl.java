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

@Service @Transactional
public class ComicServiceImpl implements ComicService {
	
	private static final Logger log = LoggerFactory.getLogger(ComicServiceImpl.class);
	private static final Marker info = MarkerFactory.getMarker("INFO");
	private static final String SERVICE = "[ComicsServiceImpl] ";
	
	@Autowired
	ComicDao cdao;

	public ComicServiceImpl(ComicDao cdao) {
		this.cdao = cdao;
	}

	@Override
	public Page<Comic> fetchAllComics(Pageable pageable) {
		log.info(info, SERVICE + "fetchAllComics");
		return cdao.findAll(pageable);
	}

}

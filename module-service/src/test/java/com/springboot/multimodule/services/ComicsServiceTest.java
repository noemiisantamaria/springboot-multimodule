package com.springboot.multimodule.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.springboot.multimodule.daos.ComicDao;
import com.springboot.multimodule.entities.Comic;

@ExtendWith(MockitoExtension.class)
public class ComicsServiceTest {

	@Mock
	private ComicDao mockComicDao;

	private ComicService comicService;

	private Pageable pageable = PageRequest.of(0, 10);
	private Page<Comic> pagedComicList = new PageImpl<Comic>(Collections.emptyList());

	@BeforeEach
	public void init() {
		comicService = new ComicServiceImpl(mockComicDao);
	}

	@Test
	public void fetchAllComicsShouldReturnComics() throws Exception {
		when(mockComicDao.findAll(any(Pageable.class))).thenReturn(pagedComicList);
		Page<Comic> list = comicService.fetchAllComics(pageable);
		assertThat(list.getTotalElements()).isNotNull();
	}

}

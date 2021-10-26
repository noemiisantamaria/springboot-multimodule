package com.springboot.multimodule.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.springboot.multimodule.daos.SuperheroDao;
import com.springboot.multimodule.entities.Superhero;
import com.springboot.multimodule.error.SuperheroNotFoundException;

@ExtendWith(MockitoExtension.class)
public class SuperheroServiceTest {
	
	@Mock
	private SuperheroDao mockSuperheroDao;
	
	private SuperheroService superheroService;
	
	private Pageable pageable = PageRequest.of(0, 10);
	private Page<Superhero> pagedHeroList = new PageImpl<Superhero>(Collections.emptyList());
	private Superhero superhero = new Superhero(1L);
	private Superhero invalidSuperhero = new Superhero();
	
	@BeforeEach
    public void init() {
		superheroService = new SuperheroServiceImpl(mockSuperheroDao);
    }

	@Test
	public void addSuperheroShouldCreate() throws Exception {
		when(mockSuperheroDao.save(any(Superhero.class))).thenReturn(superhero);
		Superhero savedSuperhero = superheroService.addSuperhero(superhero);
		assertThat(savedSuperhero.getId()).isNotNull();
	}

	@Test
	public void addSuperheroShouldReturn400() throws Exception {
		when(mockSuperheroDao.save(any(Superhero.class))).thenThrow(IllegalStateException.class);
		assertThrows(IllegalStateException.class, () -> {
			superheroService.addSuperhero(invalidSuperhero);
		});
	}

	@Test
	public void deleteSuperheroShouldDelete() throws Exception {
		when(mockSuperheroDao.findById(anyLong())).thenReturn(Optional.of(superhero));
		superheroService.deleteSuperhero(superhero.getId());
		verify(mockSuperheroDao, times(1)).deleteById(superhero.getId());
	}
	
	@Test
	public void deleteSuperheroShouldReturn404() throws Exception {
		when(mockSuperheroDao.findById(anyLong())).thenThrow(SuperheroNotFoundException.class);
		assertThrows(SuperheroNotFoundException.class, () -> {
			superheroService.deleteSuperhero(1L);
		});
	}
	
	@Test
	public void getSuperheroShouldReturnHero() throws Exception {
		when(mockSuperheroDao.findById(anyLong())).thenReturn(Optional.of(superhero));
		Superhero hero = superheroService.getSuperhero(superhero.getId());
		assertThat(hero.getId()).isNotNull();
	}
	
	@Test
	public void getSuperheroShouldReturn404() throws Exception {
		when(mockSuperheroDao.findById(anyLong())).thenThrow(SuperheroNotFoundException.class);
		assertThrows(SuperheroNotFoundException.class, () -> {
			superheroService.getSuperhero(anyLong());
		});
	}
	
	@Test
	public void fetchAllSuperheroesShouldReturnHeroes() throws Exception {
		when(mockSuperheroDao.findAll(any(Pageable.class))).thenReturn(pagedHeroList);
		Page<Superhero> list = superheroService.fetchAllSuperheroes(pageable);
		assertThat(list.getTotalElements()).isNotNull();
	}
	
	@Test
	public void updateSuperheroShouldReturnHero() throws Exception {
		when(mockSuperheroDao.findById(anyLong())).thenReturn(Optional.of(superhero));
		when(mockSuperheroDao.save(any(Superhero.class))).thenReturn(superhero);
		Superhero hero = superheroService.updateSuperhero(superhero);
		assertThat(hero.getId()).isNotNull();
	}
	
	@Test
	public void updateSuperheroShouldReturn404() throws Exception {
		when(mockSuperheroDao.findById(anyLong())).thenThrow(SuperheroNotFoundException.class);
		assertThrows(SuperheroNotFoundException.class, () -> {
			superheroService.updateSuperhero(superhero);
		});
	}

}

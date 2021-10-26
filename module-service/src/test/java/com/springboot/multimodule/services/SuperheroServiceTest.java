package com.springboot.multimodule.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.springboot.multimodule.daos.SuperheroDao;
import com.springboot.multimodule.entities.Superhero;
import com.springboot.multimodule.error.SuperheroNotFoundException;

@ExtendWith(MockitoExtension.class)
public class SuperheroServiceTest {
	
	@Mock
	private SuperheroDao mockSuperheroDao;
	
	private SuperheroService superheroService;
	
	@BeforeEach
    public void init() {
		superheroService = new SuperheroServiceImpl(mockSuperheroDao);
    }

	@Test
	public void addSuperheroShouldCreate() throws Exception {
		String name = "Natasha Romanoff";
		String description = "Black Widow";
		String thumbnail = "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg";
		Superhero superhero = new Superhero(name, description, thumbnail);
		when(mockSuperheroDao.save(any(Superhero.class))).thenReturn(superhero);
		Superhero savedSuperhero = superheroService.addSuperhero(superhero);
		assertThat(savedSuperhero.getName()).isNotNull();
	}

	@Test
	public void addSuperheroShouldReturn400() throws Exception {
		Superhero superhero = new Superhero(null, null, null);
		when(mockSuperheroDao.save(any(Superhero.class))).thenThrow(IllegalStateException.class);
		assertThrows(IllegalStateException.class, () -> {
			superheroService.addSuperhero(superhero);
		});
	}

	@Test
	public void deleteSuperheroShouldDelete() throws Exception {
		Superhero superhero = new Superhero(1L);
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

}

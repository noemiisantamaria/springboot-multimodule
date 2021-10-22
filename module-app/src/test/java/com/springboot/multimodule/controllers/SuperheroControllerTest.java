package com.springboot.multimodule.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.multimodule.entities.Superhero;

@SpringBootTest
@AutoConfigureMockMvc
public class SuperheroControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	JacksonTester<Superhero> jsonSuperhero;

	@BeforeEach
	public void setup() {
		JacksonTester.initFields(this, mapper);
	}

	@Test
	public void addSuperheroShouldCreate() throws Exception {
		String name = "Natasha Romanoff";
		String description = "Black Widow";
		String thumbnail = "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg";
		Superhero superhero = new Superhero(name, description, thumbnail);
		this.mockMvc.perform(post("/superhero/add").contentType(MediaType.APPLICATION_JSON)
				.content(jsonSuperhero.write(superhero).getJson())).andExpect(status().isCreated());
	}
	
	@Test
	public void addSuperheroShouldReturn400() throws Exception {
		Superhero superhero = new Superhero(null, null, null);
		this.mockMvc.perform(post("/superhero/add").contentType(MediaType.APPLICATION_JSON)
				.content(jsonSuperhero.write(superhero).getJson())).andExpect(status().isBadRequest());
	}

	@Test
	public void fetchAllSuperheroesShouldReturnAllHeroes() throws Exception {
		this.mockMvc.perform(get("/superheroes")).andExpect(status().isOk());
	}

	@Test
	public void getSuperheroShoudReturnHero() throws Exception {
		this.mockMvc.perform(get("/superhero/1")).andExpect(status().isOk());
	}

	@Test
	public void getSuperheroShoudReturn404() throws Exception {
		this.mockMvc.perform(get("/superhero/-1")).andExpect(status().isNotFound());
	}

}

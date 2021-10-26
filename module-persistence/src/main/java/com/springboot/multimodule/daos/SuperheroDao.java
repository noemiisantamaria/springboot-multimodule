package com.springboot.multimodule.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.multimodule.entities.Superhero;

@Repository
public interface SuperheroDao extends JpaRepository<Superhero, Long> {
	
}

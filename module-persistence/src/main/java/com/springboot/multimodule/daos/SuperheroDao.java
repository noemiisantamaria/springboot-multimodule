package com.springboot.multimodule.daos;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.multimodule.entities.Superhero;

@Repository
public interface SuperheroDao extends JpaRepository<Superhero, Long> {

}

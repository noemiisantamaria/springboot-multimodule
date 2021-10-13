package com.springboot.multimodule.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.multimodule.entities.Comic;

@Repository
public interface ComicDao extends JpaRepository<Comic, Long> {

}

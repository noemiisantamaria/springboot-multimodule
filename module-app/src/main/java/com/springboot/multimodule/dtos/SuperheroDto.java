package com.springboot.multimodule.dtos;

import lombok.Getter;
import lombok.Setter;

public class SuperheroDto {
	
	@Getter @Setter
	Long id;
	
	@Getter @Setter
	String name;
	
	@Getter @Setter
	String description;
	
	@Getter @Setter
	String thumbnail;

}

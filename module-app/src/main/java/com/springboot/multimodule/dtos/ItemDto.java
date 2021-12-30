package com.springboot.multimodule.dtos;

import lombok.Getter;
import lombok.Setter;

public class ItemDto {

	@Getter
	@Setter
	Long id;

	@Getter
	@Setter
	String name;

	@Getter
	@Setter
	String resourceUri;

}

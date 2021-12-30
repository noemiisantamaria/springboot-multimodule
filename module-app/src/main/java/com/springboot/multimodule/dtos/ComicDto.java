package com.springboot.multimodule.dtos;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

public class ComicDto {

	@Getter
	@Setter
	Long id;

	@Getter
	@Setter
	String collectionUri;

	@Getter
	@Setter
	private Set<ItemDto> items;

}

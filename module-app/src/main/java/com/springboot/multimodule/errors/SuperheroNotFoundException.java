package com.springboot.multimodule.errors;

import com.springboot.multimodule.utils.Messages;

public class SuperheroNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public SuperheroNotFoundException(Long id) {
		super(Messages.get("message.content.notFound", new Object[]{"Superhero"}) + id);
    }
}

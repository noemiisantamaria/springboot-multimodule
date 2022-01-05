package com.springboot.multimodule.errors;

import com.springboot.multimodule.common.Messages;

public class SearchParamInvalidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SearchParamInvalidException() {
		super(Messages.get("message.genericError"));
	}

	public SearchParamInvalidException(String key) {
		super(Messages.get("message.content.invalid", new Object[] { "Param" }) + key);
	}
	
}
package com.springboot.multimodule.utils;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

@Component
public class Messages {
	
	@Autowired
	private MessageSource messageSource;

	private static MessageSourceAccessor accessor;

	@PostConstruct
	private void init() {
		accessor = new MessageSourceAccessor(messageSource, Locale.ENGLISH);
	}

	public static String get(String code) {
		return accessor.getMessage(code);
	}

	public static String get(String code, Object[] args) {
		return accessor.getMessage(code,args);
	}
}

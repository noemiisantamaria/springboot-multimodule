package com.springboot.multimodule.utils;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Response {
	
	@Getter @Setter
    private Object message;
    @Getter @Setter
    private Object data;
    
    @AllArgsConstructor 
    public static class ResponseMessage {
    	@Getter @Setter
    	private int code;
    	@Getter @Setter
    	private String text;
    }
    
    @JsonSerialize 
    public static class ResponseEmpty {}

}

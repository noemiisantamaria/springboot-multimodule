package com.springboot.multimodule.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ResponseList {
	
	@Getter @Setter
    private Object message;
	@Getter @Setter
    private int page;
	@Getter @Setter
    private long items;
    @Getter @Setter
    private Object data;
    
    @AllArgsConstructor 
    public static class ResponseMessage {
    	@Getter @Setter
    	private int code;
    	@Getter @Setter
    	private String text;
    }

}

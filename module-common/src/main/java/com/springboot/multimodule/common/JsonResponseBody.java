package com.springboot.multimodule.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor 
public class JsonResponseBody {
   
	@Getter @Setter
    private int server;
    @Getter @Setter
    private Object response;
    
}
package com.springboot.multimodule.errors;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springboot.multimodule.common.JsonResponseEntity;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(SuperheroNotFoundException.class)
	protected ResponseEntity<Object> handleNotFound(HttpServletResponse response) throws IOException {
		JsonResponseEntity entity = new JsonResponseEntity(Optional.empty(), HttpStatus.NOT_FOUND);
		return ResponseEntity.status(entity.getStatus()).body(entity.getBody());
	}

	@ExceptionHandler(SearchParamInvalidException.class)
	protected ResponseEntity<Object> handleBadRequest(HttpServletResponse response) throws IOException {
		JsonResponseEntity entity = new JsonResponseEntity(Optional.empty(), HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(entity.getStatus()).body(entity.getBody());
	}

	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<Object> handleIllegalStateException(IllegalStateException ise) {
		String msg = ise.getCause().getLocalizedMessage();
		JsonResponseEntity entity = new JsonResponseEntity(HttpStatus.BAD_REQUEST, msg);
		return ResponseEntity.status(entity.getStatus()).body(entity.getBody());
	}

}

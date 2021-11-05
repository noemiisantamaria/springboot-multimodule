package com.springboot.multimodule.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public class JsonResponseEntity {
	
	private static final String MESSAGE_GENERIC_ERROR = Messages.get("message.genericError");
	private static final String MESSAGE_NOT_FOUND = Messages.get("message.notFound");
	private static final String MESSAGE_NO_RESULTS = Messages.get("message.noResults");
	private static final String MESSAGE_SUCCESSFUL = Messages.get("message.successful");

	@Getter
	@Setter
	private HttpStatus status;

	@Getter
	@Setter
	private JsonResponseBody body;

	public JsonResponseEntity(Optional<?> content, HttpStatus status) {
		if (content.isPresent()) {
			this.status = status;
			this.body = new JsonResponseBody(status.value(), new Response(new Response.ResponseMessage(0, MESSAGE_SUCCESSFUL), content.get()));
		} else {
			this.status = HttpStatus.NOT_FOUND;
			this.body = new JsonResponseBody(HttpStatus.NOT_FOUND.value(), new Response(new Response.ResponseMessage(1, MESSAGE_NOT_FOUND), new Response.ResponseEmpty()));
		}
	}
	
	public JsonResponseEntity(Optional<?> content, HttpStatus status, int code) {
		if (content.isPresent()) {
			this.status = status;
			this.body = new JsonResponseBody(status.value(), new Response(new Response.ResponseMessage(0, MESSAGE_SUCCESSFUL), content.get()));
		} else {
			if (status.value() == 400) {
				this.status = HttpStatus.BAD_REQUEST;
				this.body = new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), new Response(new Response.ResponseMessage(code, getErrorMessageFromCode(code)), new Response.ResponseEmpty()));
			} else {
				this.status = HttpStatus.NOT_FOUND;
				this.body = new JsonResponseBody(HttpStatus.NOT_FOUND.value(), new Response(new Response.ResponseMessage(code, getErrorMessageFromCode(code)), new Response.ResponseEmpty()));
			}			
		}
	}

	public JsonResponseEntity(Optional<?> content) {
		this(content,HttpStatus.OK);
	}
	
	public JsonResponseEntity(HttpStatus status, String msg) {
		this.status = status;
		this.body = new JsonResponseBody(status.value(), new Response(new Response.ResponseMessage(status.value(), msg), new Response.ResponseEmpty()));
	}

	public JsonResponseEntity(Integer page, Page<?> list) {
		if (page > (list.getTotalPages() - 1) && list.getTotalElements() > 0) {
			this.status = HttpStatus.BAD_REQUEST;
			this.body = new JsonResponseBody(HttpStatus.BAD_REQUEST.value(),
					new Response(new Response.ResponseMessage(1, MESSAGE_NOT_FOUND), new Response.ResponseEmpty()));
		} else if (list.getTotalElements() == 0) {
			this.status = HttpStatus.OK;
			this.body = new JsonResponseBody(HttpStatus.OK.value(),
					new Response(new Response.ResponseMessage(1, MESSAGE_NO_RESULTS), list.getContent()));
		} else {
			this.status = HttpStatus.OK;
			this.body = new JsonResponseBody(HttpStatus.OK.value(), new ResponseList(new ResponseList.ResponseMessage(0, MESSAGE_SUCCESSFUL),
					page, list.getTotalElements(), list.getContent()));
		}
	}
	
	public static String getErrorMessageFromCode(int code) {
		String message = null;
		switch (code) {
			case 100:
				message = MESSAGE_NOT_FOUND;
				break;
			case 101:
				message = MESSAGE_NOT_FOUND;
			default:
				message = MESSAGE_GENERIC_ERROR;
				break;
		}
		return message;
	}
}

package com.alphalabs.connectify.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ForbiddenContent extends RuntimeException {
	public ForbiddenContent(String message) {
		super(message);
	}
}
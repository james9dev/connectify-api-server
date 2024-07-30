package com.alphalabs.connectify.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnMatchTokenType extends RuntimeException {
	public UnMatchTokenType(String message) {
		super(message);
	}
}
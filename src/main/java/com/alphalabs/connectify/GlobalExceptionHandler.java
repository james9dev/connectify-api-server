package com.alphalabs.connectify;

import com.alphalabs.connectify.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NoSuchElementFoundException.class)
	public ResponseEntity<String> handleNoSuchElementFoundException(NoSuchElementFoundException e) {
		e.printStackTrace();
		//log.error("Exception [Err_Location] : {}", e.getStackTrace()[0]);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
		e.printStackTrace();
		//log.error("Exception [Err_Location] : {}", e.getStackTrace()[0]);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	}

	@ExceptionHandler(UnMatchTokenType.class)
	public ResponseEntity<String> handleRuntimeException(UnMatchTokenType e) {
		e.printStackTrace();
		//log.error("Exception [Err_Location] : {}", e.getStackTrace()[0]);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	}
}

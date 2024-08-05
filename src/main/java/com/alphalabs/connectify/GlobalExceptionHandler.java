package com.alphalabs.connectify;

import com.alphalabs.connectify.app.member.domain.AuthDomain;
import com.alphalabs.connectify.common.ResultDto;
import com.alphalabs.connectify.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NoSuchElementFoundException.class)
	public ResponseEntity<ResultDto<Object>> handleNoSuchElementFoundException(NoSuchElementFoundException e) {
		e.printStackTrace();
		//log.error("Exception [Err_Location] : {}", e.getStackTrace()[0]);

		ResultDto<Object> result = new ResultDto<>(HttpStatus.NOT_FOUND.value(), e.getMessage(), null);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
	}

	@ExceptionHandler(UnMatchTokenType.class)
	public ResponseEntity<ResultDto<Object>> handleRuntimeException(UnMatchTokenType e) {
		e.printStackTrace();
		//log.error("Exception [Err_Location] : {}", e.getStackTrace()[0]);

		ResultDto<Object> result = new ResultDto<>(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), null);

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
	}

	@ExceptionHandler(ForbiddenContent.class)
	public ResponseEntity<ResultDto<Object>> handleRuntimeException(ForbiddenContent e) {
		e.printStackTrace();
		//log.error("Exception [Err_Location] : {}", e.getStackTrace()[0]);

		ResultDto<Object> result = new ResultDto<>(HttpStatus.FORBIDDEN.value(), e.getMessage(), null);

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
	}


	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ResultDto<Object>> handleRuntimeException(RuntimeException e) {
		e.printStackTrace();
		//log.error("Exception [Err_Location] : {}", e.getStackTrace()[0]);

		ResultDto<Object> result = new ResultDto<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
	}

	@ExceptionHandler(IOException.class)
	public ResponseEntity<ResultDto<Object>> handleIOExceptionException(RuntimeException e) {
		e.printStackTrace();
		//log.error("Exception [Err_Location] : {}", e.getStackTrace()[0]);

		ResultDto<Object> result = new ResultDto<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
	}


}

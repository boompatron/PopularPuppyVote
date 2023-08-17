package com.numble.popularpuppyvote.common.exception;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({
		EntityNotFoundException.class
	})
	public ResponseEntity<ExceptionResponse> handleEntityNotFoundException(){
		return ExceptionResponse.toResponseEntity(ExceptionCode.ENTITY_NOT_FOUND);
	}
}

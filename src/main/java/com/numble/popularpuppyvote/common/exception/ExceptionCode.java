package com.numble.popularpuppyvote.common.exception;

import static org.springframework.http.HttpStatus.*;


import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionCode {

	// 400 BAD REQUEST


	// 404 NOT FOUND
	ENTITY_NOT_FOUND(NOT_FOUND, "를 찾을 수 없습니다."),


	// 409 CONFLICT

	;

	private final HttpStatus httpStatus;
	private final String message;
}

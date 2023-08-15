package com.numble.popularpuppyvote.common.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExceptionResponse {

	private final LocalDateTime timeStamp = LocalDateTime.now();
	private final HttpStatus status;
	private final String error;
	private final String code;
	private final String message;

	public static ResponseEntity<ExceptionResponse> toResponseEntity(ExceptionCode exceptionCode) {
		return ResponseEntity
			.status(exceptionCode.getHttpStatus())
			.body(
				ExceptionResponse.builder()
					.status(exceptionCode.getHttpStatus())
					.error(exceptionCode.getHttpStatus().name())
					.code(exceptionCode.name())
					.message(exceptionCode.getMessage())
					.build()
			);
	}
}

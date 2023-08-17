package com.numble.popularpuppyvote.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EntityType {
	PUPPY("강아지");

	private final String typeName;
}

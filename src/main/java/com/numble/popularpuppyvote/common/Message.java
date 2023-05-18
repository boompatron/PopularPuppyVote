package com.numble.popularpuppyvote.common;

public enum Message {
	PUPPY("강아지"),
	ENTITY_NOT_FOUND("존재하지 않는 %s 입니다");

	private final String message;

	Message(String message) {this.message = message;}
}

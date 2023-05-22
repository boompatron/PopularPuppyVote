package com.numble.popularpuppyvote.domain.model;

public enum Size {
	LARGE("대형"),
	MEDIUM("중형"),
	SMALL("소형");

	private final String name;

	Size(String name){
		this.name = name;
	}
}

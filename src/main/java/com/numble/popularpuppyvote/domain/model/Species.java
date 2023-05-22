package com.numble.popularpuppyvote.domain.model;

public enum Species {
	WELSH_CORGI("웰시코기"),
	MALTESE("말티즈"),
	SHIH_TZU("시츄"),
	DASHSHUND("닥스훈트"),
	BORDER_COLLIE("보더콜리"),
	GOLDEN_RETRIEVER("골든리트리버"),
	PUG("퍼그"),
	SAMOYED("사모예드"),
	DALMATIAN("달마시안"),
	BICHON_FRISE("비숑프리제"),
	POMERANIAN("포메라니안"),
	POODLE("푸들"),
	JINDO_DOG("진돗개"),
	SHIBA_INU("시바"),
	BIGGLE("비글"),
	GERMAN_SHEPHERD("저먼셰퍼드"),
	CHIHUAHUA("치와와"),
	BULLDOG("불독"),
	SIBERIAN_HUSKEY("허스키"),
	DOBERMAN("도베르만"),
	YORKSHIRE_TERRIER("요크셔테리어"),
	GREAT_DANE("그레이트데인"),
	CHOW_CHOW("차우차우"),
	ITALIAN_GREYHOUND("이탈리안그레이하운드"),
	MIXED("믹스");

	private final String name;

	Species(String name) {this.name = name;}
}

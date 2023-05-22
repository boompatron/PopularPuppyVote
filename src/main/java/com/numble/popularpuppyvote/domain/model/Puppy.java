package com.numble.popularpuppyvote.domain.model;

import static lombok.AccessLevel.PROTECTED;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.numble.popularpuppyvote.common.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "puppy")
@NoArgsConstructor(access = PROTECTED)
public class Puppy extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String imageUrl;

	private String description;

	@Enumerated(EnumType.STRING)
	private Species species;

	@Enumerated(EnumType.STRING)
	private Size size;

	private Integer likeCount;

	@Builder
	public Puppy(Long id, String name, String imageUrl, String description, Species species, Size size) {
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
		this.description = description;
		this.size = size;
		this.species = species;
		this.likeCount = 0;
	}

	public void updateImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public void updateDescription(String description){
		this.description = description;
	}
}

package com.numble.popularpuppyvote.domain.puppy.model;

import static lombok.AccessLevel.PROTECTED;

import javax.persistence.Entity;
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

	@Builder
	public Puppy(Long id, String name, String imageUrl, String description) {
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
		this.description = description;
	}
}

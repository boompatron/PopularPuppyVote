package com.numble.popularpuppyvote.domain.likes.model;

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
@Table(name = "likes")
@NoArgsConstructor(access = PROTECTED)
public class Likes extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long puppyId;

	boolean isDeleted;

	@Builder
	public Likes(Long id, Long puppyId) {
		this.id = id;
		this.puppyId = puppyId;
		this.isDeleted = false;
	}
}

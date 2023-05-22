package com.numble.popularpuppyvote.domain.model;

import static lombok.AccessLevel.PROTECTED;

import java.time.LocalDateTime;

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

	private Boolean isDeleted;

	@Builder
	public Likes(Long id, Long puppyId) {
		this.id = id;
		this.puppyId = puppyId;
		this.isDeleted = false;
	}

	public Likes(Long id, Long puppyId, Boolean isDeleted, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.puppyId = puppyId;
		this.isDeleted = isDeleted;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public void delete(){
		this.isDeleted = true;
	}
}

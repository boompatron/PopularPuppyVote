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
	private String sessionId;

	@Builder
	public Likes(Long id, Long puppyId, String sessionId) {
		this.id = id;
		this.puppyId = puppyId;
		this.isDeleted = false;
		this.sessionId = sessionId;
	}

	public Likes(Long id, Long puppyId, Boolean isDeleted, LocalDateTime createdAt, LocalDateTime updatedAt, String sessionId) {
		this.id = id;
		this.puppyId = puppyId;
		this.isDeleted = isDeleted;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.sessionId = sessionId;
	}

	public void delete() {
		this.isDeleted = true;
	}

	@Override
	public String toString() {
		return "ID : " + this.id + ", puppyId : " + puppyId + ", isDeleted : " + isDeleted + ", session Id : " + sessionId;
	}
}

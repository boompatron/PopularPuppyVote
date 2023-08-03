package com.numble.popularpuppyvote.domain.repository;

import java.util.List;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.numble.popularpuppyvote.domain.model.Likes;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LikesJdbcRepository {
	private final NamedParameterJdbcTemplate jdbcTemplate;
	private static final String TABLE = "likes";

	public void bulkInsert(List<Likes> likesList) {
		String sql = String.format("""
				INSERT INTO `%s` (puppy_id, is_deleted, created_at, updated_at, session_id)
				VALUES (:puppyId, :isDeleted, :createdAt, :updatedAt, :sessionId)
				""", TABLE);

		SqlParameterSource[] params = likesList
				.stream()
				.map(BeanPropertySqlParameterSource::new)
				.toArray(SqlParameterSource[]::new);
		jdbcTemplate.batchUpdate(sql, params);
	}
}

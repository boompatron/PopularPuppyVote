package com.numble.popularpuppyvote.domain.repository;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
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
	private static final RowMapper<Likes> ROW_MAPPER = (ResultSet resultSet, int rowNumber) ->
			new Likes(
					resultSet.getLong("id"),
					resultSet.getLong("puppyId"),
					resultSet.getBoolean("isDeleted"),
					resultSet.getObject("createdAt", LocalDateTime.class),
					resultSet.getObject("updatedAt", LocalDateTime.class)
			);

	public void bulkInsert(List<Likes> likesList) {
		String sql = String.format("""
				INSERT INTO `%s` (puppy_id, is_deleted, created_at, updated_at)
				VALUES (:puppyId, :isDeleted, :createdAt, :updatedAt)
				""", TABLE);

		SqlParameterSource[] params = likesList
				.stream()
				.map(BeanPropertySqlParameterSource::new)
				.toArray(SqlParameterSource[]::new);

		jdbcTemplate.batchUpdate(sql, params);
	}
}

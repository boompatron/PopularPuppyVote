package com.numble.popularpuppyvote.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.numble.popularpuppyvote.domain.model.Likes;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {

	@Query("SELECT COUNT(*) FROM Likes as l WHERE l.puppyId = :puppyId")
	public Long getLikesCountByPuppyId(Long puppyId);

	// TODO distinct vs group by 속도 차이 뭐 쓸지 결정하기
	@Query("SELECT DISTINCT l.puppyId FROM Likes as l")
	public List<Long> getPuppyIdsDistinct();

	@Query("SELECT l.puppyId FROM Likes as l GROUP BY l.puppyId")
	public List<Long> getPuppyIdsGroupBy();

	@Query("SELECT COUNT(*) FROM Likes as l GROUP BY l.puppyId")
	public List<Object> getAllCountGroupByPuppyId();
}

package com.numble.popularpuppyvote.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.numble.popularpuppyvote.domain.model.Likes;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {

	@Query("SELECT COUNT(*) FROM Likes as l WHERE l.puppyId = :puppyId")
	Integer getLikesCountByPuppyId(Long puppyId);

	@Query("SELECT DISTINCT l.puppyId FROM Likes as l")
	List<Long> getPuppyIdsDistinct();

	// Group by 가 더 빠름
	@Query("SELECT l.puppyId FROM Likes as l GROUP BY l.puppyId")
	List<Long> getPuppyIdsGroupBy();

	@Query("SELECT COUNT(*) FROM Likes as l WHERE l.puppyId = :puppyId")
	int getLikesByPuppyId(Long puppyId);
}

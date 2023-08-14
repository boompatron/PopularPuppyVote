package com.numble.popularpuppyvote.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.numble.popularpuppyvote.domain.model.Puppy;

@Repository
public interface PuppyRepository extends JpaRepository<Puppy, Long>, PuppyCustomRepository {

	@Query("SELECT p.id FROM Puppy as p GROUP BY p.id")
	List<Long> getAllIdsGroupBy();

	@Query("SELECT DISTINCT p.id FROM Puppy as p")
	List<Long> getAllIdsDistinct();

	@Query("SELECT DISTINCT p.id FROM Puppy as p GROUP BY p.id")
	List<Long> getAllIdsDistinctGroupBy();

	// List<Puppy> getPuppyByIdIn(List<Long> ids);

	@Modifying
	@Query("UPDATE Puppy as p SET p.likeCount = :likeCount WHERE p.id = :puppyId")
	int updateLikeCountById(Long puppyId, Integer likeCount);
}

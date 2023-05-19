package com.numble.popularpuppyvote.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.numble.popularpuppyvote.domain.model.Likes;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
}

package com.numble.popularpuppyvote.domain.puppy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.numble.popularpuppyvote.domain.puppy.model.Puppy;

@Repository
public interface PuppyRepository extends JpaRepository<Puppy, Long> {
}

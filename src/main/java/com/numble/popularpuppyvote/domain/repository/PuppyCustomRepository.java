package com.numble.popularpuppyvote.domain.repository;

import java.util.List;

import com.numble.popularpuppyvote.domain.model.Puppy;

public interface PuppyCustomRepository {

	List<Puppy> findPuppies(Long cursorId, int pageSize);
}

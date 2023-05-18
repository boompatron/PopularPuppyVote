package com.numble.popularpuppyvote.domain.puppy.repository;

import java.util.List;

import com.numble.popularpuppyvote.domain.puppy.model.Puppy;

public interface PuppyCustomRepository {

	List<Puppy> findPuppies(Long cursorId, int pageSize);
}

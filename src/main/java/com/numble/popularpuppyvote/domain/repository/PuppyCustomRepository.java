package com.numble.popularpuppyvote.domain.repository;

import java.util.List;

import com.numble.popularpuppyvote.domain.model.Puppy;
import com.numble.popularpuppyvote.domain.model.Size;
import com.numble.popularpuppyvote.domain.model.Species;

public interface PuppyCustomRepository {

	List<Puppy> findPuppies(Long cursorId, int pageSize);

	List<Puppy> findPuppiesWithFiltering(Long cursorId, int pageSize, List<Species> species, List<Size> sizes);
}

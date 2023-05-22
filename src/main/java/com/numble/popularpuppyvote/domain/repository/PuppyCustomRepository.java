package com.numble.popularpuppyvote.domain.repository;

import java.util.List;

import com.numble.popularpuppyvote.domain.model.Puppy;
import com.numble.popularpuppyvote.domain.model.Size;
import com.numble.popularpuppyvote.domain.model.SortingCriteria;
import com.numble.popularpuppyvote.domain.model.Species;

public interface PuppyCustomRepository {

	List<Puppy> findPuppies(Long cursorId, int pageSize);

	// TODO 포함이 아니라 제외하는 필터링도 구현
	List<Puppy> findPuppiesWithFiltering(Long cursorId, int pageSize, List<Species> species, List<Size> sizes);

	List<Puppy> findPuppiesWithSorting(Long compareId, int pageSize, SortingCriteria criteria, Boolean isAscending);
}

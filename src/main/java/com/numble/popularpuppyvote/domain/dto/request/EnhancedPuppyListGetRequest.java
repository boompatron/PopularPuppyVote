package com.numble.popularpuppyvote.domain.dto.request;

import java.util.List;

import com.numble.popularpuppyvote.domain.model.Size;
import com.numble.popularpuppyvote.domain.model.SortingCriteria;
import com.numble.popularpuppyvote.domain.model.Species;

public record EnhancedPuppyListGetRequest(
		Long cursorId,
		int pageSize,
		List<Species> species,
		List<Size> sizes,
		SortingCriteria criteria,
		Boolean isAscending
) {
}

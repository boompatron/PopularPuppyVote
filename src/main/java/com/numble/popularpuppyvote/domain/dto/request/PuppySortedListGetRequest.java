package com.numble.popularpuppyvote.domain.dto.request;

import com.numble.popularpuppyvote.domain.model.SortingCriteria;

public record PuppySortedListGetRequest(
		Long cursorId,
		int pageSize,
		SortingCriteria criteria,
		Boolean isAscending
) {
}

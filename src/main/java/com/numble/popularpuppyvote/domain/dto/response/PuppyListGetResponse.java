package com.numble.popularpuppyvote.domain.dto.response;

import java.util.List;

public record PuppyListGetResponse(
		List<PuppyGetResponse> puppies,
		Long lastId
) {
}

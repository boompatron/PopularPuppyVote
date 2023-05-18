package com.numble.popularpuppyvote.domain.puppy.dto.request;

public record PuppyListGetRequest(
		Long cursorId,
		int pageSize
) {
}

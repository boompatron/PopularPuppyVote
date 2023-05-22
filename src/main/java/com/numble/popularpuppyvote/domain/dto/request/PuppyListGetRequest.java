package com.numble.popularpuppyvote.domain.dto.request;

public record PuppyListGetRequest(
		Long cursorId,
		int pageSize
) {
}

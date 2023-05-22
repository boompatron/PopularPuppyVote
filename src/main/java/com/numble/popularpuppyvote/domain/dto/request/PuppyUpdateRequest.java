package com.numble.popularpuppyvote.domain.dto.request;

public record PuppyUpdateRequest(
		Long id,
		String imageUrl,
		String description
) {
}

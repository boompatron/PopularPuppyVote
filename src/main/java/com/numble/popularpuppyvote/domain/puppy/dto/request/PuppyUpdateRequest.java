package com.numble.popularpuppyvote.domain.puppy.dto.request;

public record PuppyUpdateRequest(
		Long id,
		String imageUrl,
		String description
) {
}

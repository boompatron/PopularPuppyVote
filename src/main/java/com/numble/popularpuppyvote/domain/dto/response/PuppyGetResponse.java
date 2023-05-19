package com.numble.popularpuppyvote.domain.dto.response;

public record PuppyGetResponse(
		Long id,
		String name,
		String imageUrl,
		String description,
		int likeCount
) {
}

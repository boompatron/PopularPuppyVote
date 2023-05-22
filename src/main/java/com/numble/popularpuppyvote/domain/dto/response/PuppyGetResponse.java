package com.numble.popularpuppyvote.domain.dto.response;

import com.numble.popularpuppyvote.domain.model.Size;
import com.numble.popularpuppyvote.domain.model.Species;

public record PuppyGetResponse(
		Long id,
		String name,
		String imageUrl,
		String description,
		Species species,
		Size size,
		int likeCount
) {
}

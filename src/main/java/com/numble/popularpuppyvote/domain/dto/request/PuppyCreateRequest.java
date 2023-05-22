package com.numble.popularpuppyvote.domain.dto.request;

import com.numble.popularpuppyvote.domain.model.Size;
import com.numble.popularpuppyvote.domain.model.Species;

public record PuppyCreateRequest (
		String name,
		String imageUrl,
		String description,
		Species species,
		Size size
) {

}

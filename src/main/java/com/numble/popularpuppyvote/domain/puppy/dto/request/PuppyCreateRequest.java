package com.numble.popularpuppyvote.domain.puppy.dto.request;

public record PuppyCreateRequest (
		String name,
		String imageUrl,
		String description
) {

}

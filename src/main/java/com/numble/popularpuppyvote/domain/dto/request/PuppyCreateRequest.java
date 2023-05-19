package com.numble.popularpuppyvote.domain.dto.request;

public record PuppyCreateRequest (
		String name,
		String imageUrl,
		String description
) {

}

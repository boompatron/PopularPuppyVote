package com.numble.popularpuppyvote.common.mapper;

import com.numble.popularpuppyvote.domain.puppy.dto.request.PuppyCreateRequest;
import com.numble.popularpuppyvote.domain.puppy.dto.response.PuppyCreateResponse;
import com.numble.popularpuppyvote.domain.puppy.model.Puppy;

public class PuppyMapper {
	public static Puppy toPuppy(PuppyCreateRequest request){
		return Puppy.builder()
				.name(request.name())
				.imageUrl(request.imageUrl())
				.description(request.description())
				.build();
	}

	public static PuppyCreateResponse toPuppyCreateResponse(Puppy puppy){
		return new PuppyCreateResponse(puppy.getId());
	}
}

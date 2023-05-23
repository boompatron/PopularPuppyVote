package com.numble.popularpuppyvote.common.mapper;

import java.util.ArrayList;
import java.util.List;

import com.numble.popularpuppyvote.domain.dto.request.PuppyCreateRequest;
import com.numble.popularpuppyvote.domain.dto.response.PuppyCreateResponse;
import com.numble.popularpuppyvote.domain.dto.response.PuppyGetResponse;
import com.numble.popularpuppyvote.domain.dto.response.PuppyListGetResponse;
import com.numble.popularpuppyvote.domain.dto.response.PuppyUpdateResponse;
import com.numble.popularpuppyvote.domain.model.Puppy;

public class PuppyMapper {
	public static Puppy toPuppy(PuppyCreateRequest request){
		return Puppy.builder()
				.name(request.name())
				.imageUrl(request.imageUrl())
				.description(request.description())
				.species(request.species())
				.size(request.size())
				.build();
	}

	public static PuppyCreateResponse toPuppyCreateResponse(Puppy puppy){
		return new PuppyCreateResponse(puppy.getId());
	}


	public static PuppyGetResponse toPuppyGetResponse(Puppy puppy){
		return new PuppyGetResponse(
				puppy.getId(),
				puppy.getName(),
				puppy.getImageUrl(),
				puppy.getDescription(),
				puppy.getSpecies(),
				puppy.getSize(),
				puppy.getLikeCount()
		);
	}


	public static PuppyListGetResponse toPuppiesGetResponse(List<Puppy> puppies, Long lastId){
		List<PuppyGetResponse> puppyresponses = new ArrayList<>();
		for (Puppy puppy : puppies) {
			puppyresponses.add(toPuppyGetResponse(puppy));
		}

		return new PuppyListGetResponse(puppyresponses, lastId);
	}


	public static PuppyUpdateResponse toPuppyUpdateResponse(Puppy puppy){
		return new PuppyUpdateResponse(puppy.getId());
	}
}

package com.numble.popularpuppyvote.domain.dto.response;

import java.util.List;

public record PuppyRankingGetResponse(
	List<PuppyGetResponse> puppies,
	Long lastRanking
) {
}

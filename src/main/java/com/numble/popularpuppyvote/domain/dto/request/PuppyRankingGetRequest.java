package com.numble.popularpuppyvote.domain.dto.request;

public record PuppyRankingGetRequest(
	Long startRanking,
	Long endRanking
) {
}

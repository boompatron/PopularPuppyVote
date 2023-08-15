package com.numble.popularpuppyvote.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "강아지 순위별로 조회 요쳥")
public record PuppyRankingGetRequest(
	@Schema(description = "조회 시작 순위", example = "0", requiredMode = Schema.RequiredMode.REQUIRED)
	Long startRanking,

	@Schema(description = "조회 끝 순위", example = "9", requiredMode = Schema.RequiredMode.REQUIRED)
	Long endRanking
) {
}

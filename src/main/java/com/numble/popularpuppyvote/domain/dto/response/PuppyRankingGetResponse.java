package com.numble.popularpuppyvote.domain.dto.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "강아지 랭킹 목록 조회 응답")
public record PuppyRankingGetResponse(

	@Schema(description = "강아지 상세 정보 목록", requiredMode = Schema.RequiredMode.REQUIRED)
	List<PuppyGetResponse> puppies,

	@Schema(description = "최하위 강아지 순위", requiredMode = Schema.RequiredMode.REQUIRED)
	Long lastRanking
) {
}

package com.numble.popularpuppyvote.domain.dto.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "강아지 목록 조회 응답")
public record PuppyListGetResponse(

	@Schema(description = "강아지 상세 정보 목록", requiredMode = Schema.RequiredMode.REQUIRED)
	List<PuppyGetResponse> puppies,

	@Schema(description = "목록 중 마지막 강아지 식별자", requiredMode = Schema.RequiredMode.REQUIRED)
	Long lastId
) {
}

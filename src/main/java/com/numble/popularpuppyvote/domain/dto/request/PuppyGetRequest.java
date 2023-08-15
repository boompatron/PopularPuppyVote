package com.numble.popularpuppyvote.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "강아지 단일 상세 조회 요청")
public record PuppyGetRequest(

	@Schema(description = "조회하고자 하는 강아지 ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
	Long id
) {
}

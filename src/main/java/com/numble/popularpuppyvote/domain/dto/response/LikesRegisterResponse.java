package com.numble.popularpuppyvote.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "강아지 좋아요 등록 응답")
public record LikesRegisterResponse(

	@Schema(description = "좋아요 식별자", example = "487", requiredMode = Schema.RequiredMode.REQUIRED)
	Long id
) {
}

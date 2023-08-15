package com.numble.popularpuppyvote.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "좋아요 등록 요청")
public record LikesRegisterRequest(

	@Schema(description = "좋아요를 누른 강아지 식별자", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
	Long puppyId,

	@Schema(description = "좋아요를 누른 사용자의 세션 ID", example = "exampleSessionId", requiredMode = Schema.RequiredMode.REQUIRED)
	String sessionId
) {
}

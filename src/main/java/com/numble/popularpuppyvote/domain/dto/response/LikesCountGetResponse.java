package com.numble.popularpuppyvote.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "강아지 좋아요 수 응답")
public record LikesCountGetResponse(

	@Schema(description = "강아지 좋아요 수", example = "34234", requiredMode = Schema.RequiredMode.REQUIRED)
	int count
) {
}

package com.numble.popularpuppyvote.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "강아지 수정 응답")
public record PuppyUpdateResponse(

	@Schema(description = "수정된 강아지 식별자", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
	Long id
) {
}

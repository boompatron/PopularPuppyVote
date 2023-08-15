package com.numble.popularpuppyvote.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "강아지 정보 수정 요청")
public record PuppyUpdateRequest(

	@Schema(description = "수정하고자 하는 강아지 ID", example = "춘향이", requiredMode = Schema.RequiredMode.REQUIRED)
	Long id,

	@Schema(description = "강아지 이미지 주소", example = "changedImage.com", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	String imageUrl,

	@Schema(description = "강아지 상세 정보", example = "원래 이름은 춘향이 였답니다!", requiredMode = Schema.RequiredMode.REQUIRED)
	String description
) {
}

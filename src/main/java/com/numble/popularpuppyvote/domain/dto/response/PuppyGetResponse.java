package com.numble.popularpuppyvote.domain.dto.response;

import com.numble.popularpuppyvote.domain.model.Size;
import com.numble.popularpuppyvote.domain.model.Species;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "강아지 단일 상세 조회 응답")
public record PuppyGetResponse(

	@Schema(description = "강아지 식별자", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	Long id,

	@Schema(description = "강아지 이름", example = "코코", requiredMode = Schema.RequiredMode.REQUIRED)
	String name,

	@Schema(description = "강아지 사진 주소", example = "coco.com", requiredMode = Schema.RequiredMode.REQUIRED)
	String imageUrl,

	@Schema(description = "강아지 상세 설명", example = "코코 좋아", requiredMode = Schema.RequiredMode.REQUIRED)
	String description,

	@Schema(description = "강아지 종류", example = "WELSH_CORGI", requiredMode = Schema.RequiredMode.REQUIRED)
	Species species,

	@Schema(description = "강아지 크기", example = "MEDIUM", requiredMode = Schema.RequiredMode.REQUIRED)
	Size size,

	@Schema(description = "좋아요 ", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
	int likeCount
) {
}

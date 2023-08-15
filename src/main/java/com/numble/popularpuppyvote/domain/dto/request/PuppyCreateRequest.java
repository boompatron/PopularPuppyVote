package com.numble.popularpuppyvote.domain.dto.request;

import com.numble.popularpuppyvote.domain.model.Size;
import com.numble.popularpuppyvote.domain.model.Species;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "강아지 등록 요청")
public record PuppyCreateRequest(

	@Schema(description = "강아지 이름", example = "코코", requiredMode = Schema.RequiredMode.REQUIRED)
	String name,

	@Schema(description = "강아지 사진 주소", example = "coco.com", requiredMode = Schema.RequiredMode.REQUIRED)
	String imageUrl,

	@Schema(description = "강아지 상세 정보", example = "귀여운건 코코, 코코는 귀여워!", requiredMode = Schema.RequiredMode.REQUIRED)
	String description,

	@Schema(description = "강아지 종", example = "WELSH_CORGI", requiredMode = Schema.RequiredMode.REQUIRED)
	Species species,

	@Schema(description = "강아지 크기", example = "MEDIUM", requiredMode = Schema.RequiredMode.REQUIRED)
	Size size
) {

}

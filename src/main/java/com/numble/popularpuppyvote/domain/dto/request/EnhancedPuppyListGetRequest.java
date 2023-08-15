package com.numble.popularpuppyvote.domain.dto.request;

import java.util.List;

import com.numble.popularpuppyvote.domain.model.Size;
import com.numble.popularpuppyvote.domain.model.SortingCriteria;
import com.numble.popularpuppyvote.domain.model.Species;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "향상된 검색 요청")
public record EnhancedPuppyListGetRequest(
	@Schema(description = "검색 시작 ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
	Long cursorId,

	@Schema(description = "검색 페이징 크기", example = "5", requiredMode = Schema.RequiredMode.REQUIRED)
	int pageSize,

	@Schema(description = "검색하려는 종", example = "WELSH_CORGI", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	List<Species> species,

	@Schema(description = "검색 하고자 하는 강아지의 크기", example = "MEDIUM", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	List<Size> sizes,

	@Schema(description = "정렬 기준", example = "LIKES", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	SortingCriteria criteria,

	@Schema(description = "검색 시 오름차순 여부", example = "false", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	Boolean isAscending
) {
}

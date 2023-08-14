package com.numble.popularpuppyvote.domain.dto.request;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.numble.popularpuppyvote.domain.model.Size;
import com.numble.popularpuppyvote.domain.model.SortingCriteria;
import com.numble.popularpuppyvote.domain.model.Species;

@ApiModel("향상된 검색 요청")
public record EnhancedPuppyListGetRequest(
	@ApiModelProperty(value = "검색 시작 ID", required = true, example = "1")
	Long cursorId,

	@ApiModelProperty(value = "검색 페이징 크기", required = true, example = "5")
	int pageSize,

	@ApiModelProperty(value = "검색하려는 종", required = false, example = "WELSH_CORGI")
	List<Species> species,

	@ApiModelProperty(value = "검색 하고자 하는 강아지의 크기", required = false, example = "MEDIUM")
	List<Size> sizes,

	@ApiModelProperty(value = "정렬 기준", required = false, example = "LIKES")
	SortingCriteria criteria,

	@ApiModelProperty(value = "검색 시 오름차순 여부", required = false, example = "false")
	Boolean isAscending
) {
}

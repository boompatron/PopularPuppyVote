package com.numble.popularpuppyvote.domain.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("강아지 단일 상세 조회 요청")
public record PuppyGetRequest(

	@ApiModelProperty(name = "조회하고자 하는 강아지 ID", required = true, example = "1")
	Long id
) {
}

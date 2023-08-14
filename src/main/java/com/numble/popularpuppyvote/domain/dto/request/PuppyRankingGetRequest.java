package com.numble.popularpuppyvote.domain.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("강아지 순위별로 조회 요쳥")
public record PuppyRankingGetRequest(
	@ApiModelProperty(name = "조회 시작 순위", required = true, example = "0")
	Long startRanking,

	@ApiModelProperty(name = "조회 끝 순위", required = true, example = "9")
	Long endRanking
) {
}

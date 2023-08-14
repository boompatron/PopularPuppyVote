package com.numble.popularpuppyvote.domain.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("강아지 정보 수정 요청")
public record PuppyUpdateRequest(

	@ApiModelProperty(name = "수정하고자 하는 강아지 ID", required = true, example = "춘향이")
	Long id,

	@ApiModelProperty(name = "강아지 이미지 주소", required = false, example = "changedImage.com")
	String imageUrl,

	@ApiModelProperty(name = "강아지 상세 정보", required = true, example = "원래 이름은 춘향이 였답니다!")
	String description
) {
}

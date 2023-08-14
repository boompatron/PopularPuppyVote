package com.numble.popularpuppyvote.domain.dto.request;

import com.numble.popularpuppyvote.domain.model.Size;
import com.numble.popularpuppyvote.domain.model.Species;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("강아지 등록 요청")
public record PuppyCreateRequest(

	@ApiModelProperty(value = "강아지 이름", required = true, example = "코코")
	String name,

	@ApiModelProperty(value = "강아지 사진 주소", required = true, example = "image@dkjfslkfj.com")
	String imageUrl,

	@ApiModelProperty(value = "강아지 상세 정보", required = true, example = "귀여운건 코코, 코코는 귀여워!")
	String description,

	@ApiModelProperty(value = "강아지 종", required = true, example = "WELSH_CORGI")
	Species species,

	@ApiModelProperty(value = "강아지 크기", required = true, example = "MEDIUM")
	Size size
) {

}

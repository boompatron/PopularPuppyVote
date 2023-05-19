package com.numble.popularpuppyvote.common.mapper;

import com.numble.popularpuppyvote.domain.likes.dto.request.LikesRegisterRequest;
import com.numble.popularpuppyvote.domain.likes.dto.response.LikesRegisterResponse;
import com.numble.popularpuppyvote.domain.likes.model.Likes;

public class LikesMapper {

	public static Likes toLikes(LikesRegisterRequest request){
		return Likes.builder()
				.puppyId(request.puppyId())
				.build();
	}

	public static LikesRegisterResponse toLikesRegisterResponse(Likes likes) {
		return new LikesRegisterResponse(likes.getId());
	}
}
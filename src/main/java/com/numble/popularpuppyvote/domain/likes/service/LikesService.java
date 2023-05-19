package com.numble.popularpuppyvote.domain.likes.service;

import static com.numble.popularpuppyvote.common.mapper.LikesMapper.toLikes;
import static com.numble.popularpuppyvote.common.mapper.LikesMapper.toLikesRegisterResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.numble.popularpuppyvote.domain.likes.dto.request.LikesRegisterRequest;
import com.numble.popularpuppyvote.domain.likes.dto.response.LikesRegisterResponse;
import com.numble.popularpuppyvote.domain.likes.repository.LikesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikesService {
	private final LikesRepository likesRepository;

	@Transactional
	public LikesRegisterResponse registerLikes(LikesRegisterRequest request){
		return toLikesRegisterResponse(likesRepository.save(toLikes(request)));
	}
}

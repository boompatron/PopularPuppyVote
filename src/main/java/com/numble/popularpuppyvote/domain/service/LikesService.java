package com.numble.popularpuppyvote.domain.service;

import static com.numble.popularpuppyvote.common.Message.ENTITY_NOT_FOUND;
import static com.numble.popularpuppyvote.common.Message.LIKES;
import static com.numble.popularpuppyvote.common.mapper.LikesMapper.toLikes;
import static com.numble.popularpuppyvote.common.mapper.LikesMapper.toLikesRegisterResponse;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.numble.popularpuppyvote.domain.dto.request.LikesRegisterRequest;
import com.numble.popularpuppyvote.domain.dto.response.LikesCountGetResponse;
import com.numble.popularpuppyvote.domain.dto.response.LikesRegisterResponse;
import com.numble.popularpuppyvote.domain.model.Likes;
import com.numble.popularpuppyvote.domain.repository.LikesRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikesService {

	private final LikesRepository likesRepository;

	@Transactional
	public LikesRegisterResponse registerLikes(LikesRegisterRequest request) {
		return toLikesRegisterResponse(likesRepository.save(toLikes(request)));
	}

	@Transactional
	public void deleteLikes(Long likesId) {
		Likes likes = getEntity(likesId);
		likes.delete();
		likesRepository.save(likes);
	}

	@Transactional(readOnly = true)
	public LikesCountGetResponse getLikesByPuppyId(Long puppyId) {
		return new LikesCountGetResponse(likesRepository.getLikesByPuppyId(puppyId));
	}

	private Likes getEntity(Long likesId) {
		return likesRepository.findById(likesId)
				.orElseThrow(() -> new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.name(), LIKES.name())));
	}
}

package com.numble.popularpuppyvote.domain.service;

import static com.numble.popularpuppyvote.common.Message.ENTITY_NOT_FOUND;
import static com.numble.popularpuppyvote.common.Message.PUPPY;
import static com.numble.popularpuppyvote.common.mapper.LikesMapper.toLikes;
import static com.numble.popularpuppyvote.common.mapper.LikesMapper.toLikesRegisterResponse;

import java.util.Objects;

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
	public void deleteLikes(String sessionId, Long puppyId) {
		Likes likes = getEntity(sessionId, puppyId);
		likes.delete();
		likesRepository.save(likes);
	}

	@Transactional(readOnly = true)
	public LikesCountGetResponse getLikesByPuppyId(Long puppyId) {
		return new LikesCountGetResponse(likesRepository.getLikesByPuppyId(puppyId));
	}

	private Likes getEntity(String sessionId, Long puppyId) {
		Likes entity = likesRepository.findBySessionIdAndPuppyId(sessionId, puppyId);
		if (Objects.isNull(entity))
			throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.name(), PUPPY.name()));
		return entity;
		//.orElseThrow(() -> new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.name(), PUPPY.name())));
	}
}

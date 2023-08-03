package com.numble.popularpuppyvote.domain.service;

import static com.numble.popularpuppyvote.common.Message.ENTITY_NOT_FOUND;
import static com.numble.popularpuppyvote.common.Message.PUPPY;
import static com.numble.popularpuppyvote.common.mapper.PuppyMapper.toPuppiesGetResponse;
import static com.numble.popularpuppyvote.common.mapper.PuppyMapper.toPuppyGetResponse;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.numble.popularpuppyvote.domain.dto.request.EnhancedPuppyListGetRequest;
import com.numble.popularpuppyvote.domain.dto.request.PuppyListGetRequest;
import com.numble.popularpuppyvote.domain.dto.response.PuppyGetResponse;
import com.numble.popularpuppyvote.domain.dto.response.PuppyListGetResponse;
import com.numble.popularpuppyvote.domain.model.Puppy;
import com.numble.popularpuppyvote.domain.repository.PuppyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PuppyReadService {

	private final PuppyRepository puppyRepository;
	private final RedisTemplate<String, PuppyGetResponse> redisTemplate;

	private static final String REDIS_PUPPY_KEY_PREFIX = "PUPPY";

	@Transactional(readOnly = true)
	public PuppyGetResponse getOnePuppy(Long puppyId) {
		ValueOperations<String, PuppyGetResponse> ops = redisTemplate.opsForValue();
		String key = REDIS_PUPPY_KEY_PREFIX + puppyId;
		if (ops.get(key) != null) {
			return ops.get(key);
		} else {
			PuppyGetResponse dto = toPuppyGetResponse(getEntity(puppyId));
			ops.set(key, dto);
			return dto;
		}
	}

	@Transactional(readOnly = true)
	public PuppyListGetResponse getPuppies(PuppyListGetRequest request) {

		List<Puppy> puppies = puppyRepository.findPuppies(request.cursorId(), request.pageSize());
		long lastId = puppies.size() > 0 ? puppies.get(puppies.size() - 1).getId() : -1L;

		return toPuppiesGetResponse(puppies, lastId);
	}

	@Transactional(readOnly = true)
	public PuppyListGetResponse getManyPuppiesWithCondition(EnhancedPuppyListGetRequest request) {
		List<Puppy> puppies = puppyRepository.findPuppies(
				request.cursorId(), request.pageSize(),
				request.species(), request.sizes(),
				request.criteria(), request.isAscending());
		long lastId = puppies.size() > 0 ? puppies.get(puppies.size() - 1).getId() : -1L;

		return toPuppiesGetResponse(puppies, lastId);
	}

	private Puppy getEntity(Long id) {
		return puppyRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.name(), PUPPY.name())));
	}
}
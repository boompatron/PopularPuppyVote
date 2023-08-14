package com.numble.popularpuppyvote.domain.service;

import static com.numble.popularpuppyvote.common.Message.ENTITY_NOT_FOUND;
import static com.numble.popularpuppyvote.common.Message.PUPPY;
import static com.numble.popularpuppyvote.common.mapper.PuppyMapper.toPuppiesGetResponse;
import static com.numble.popularpuppyvote.common.mapper.PuppyMapper.toPuppyGetResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.numble.popularpuppyvote.common.mapper.PuppyMapper;
import com.numble.popularpuppyvote.domain.dto.request.EnhancedPuppyListGetRequest;
import com.numble.popularpuppyvote.domain.dto.request.PuppyRankingGetRequest;
import com.numble.popularpuppyvote.domain.dto.response.PuppyGetResponse;
import com.numble.popularpuppyvote.domain.dto.response.PuppyListGetResponse;
import com.numble.popularpuppyvote.domain.model.Puppy;
import com.numble.popularpuppyvote.domain.repository.PuppyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PuppyReadService {

	private final PuppyRepository puppyRepository;
	private final RedisTemplate<String, PuppyGetResponse> redisTemplate;
	private final StringRedisTemplate stringRedisTemplate;

	private static final String REDIS_PUPPY_KEY_PREFIX = "PUPPY";
	private static final String RANKING_KEY = "LIKE_RANKING";

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
	public PuppyListGetResponse getManyPuppiesWithCondition(EnhancedPuppyListGetRequest request) {
		List<Puppy> puppies = puppyRepository.findPuppies(
			request.cursorId(), request.pageSize(),
			request.species(), request.sizes(),
			request.criteria(), request.isAscending());
		long lastId = puppies.size() > 0 ? puppies.get(puppies.size() - 1).getId() : -1L;

		return toPuppiesGetResponse(puppies, lastId);
	}

	@Transactional
	public PuppyListGetResponse getPuppiesByRanking(PuppyRankingGetRequest request) {
		ZSetOperations<String, String> zSet = stringRedisTemplate.opsForZSet();
		List<String> rankingList = new ArrayList<>(
			zSet.reverseRange(RANKING_KEY, request.startRanking(), request.endRanking()));
		ValueOperations<String, PuppyGetResponse> ops = redisTemplate.opsForValue();

		List<Long> unCachedIds = new ArrayList<>();
		for (String id : rankingList) {
			if (ops.get(id) == null) {
				unCachedIds.add(Long.parseLong(id));
			}
		}

		Map<Long, PuppyGetResponse> unCachedDtos = new HashMap<>();
		if (unCachedIds.size() > 0) {
			unCachedDtos = getPuppiesByIds(unCachedIds);
			for (Long id: unCachedIds) {
				ops.set(id.toString(), unCachedDtos.get(id));
			}
		}
		List<PuppyGetResponse> puppyDtos = new ArrayList<>();
		for (String id : rankingList) {
			puppyDtos.add((ops.get(id) != null) ? ops.get(id) : unCachedDtos.get(Long.parseLong(id)));
		}

		return new PuppyListGetResponse(puppyDtos, request.endRanking());
	}

	private Map<Long, PuppyGetResponse> getPuppiesByIds(List<Long> ids) {

		List<PuppyGetResponse> pList = puppyRepository.findPuppiesByIds(ids)
			.stream()
			.map(PuppyMapper::toPuppyGetResponse)
			.toList();
		Map<Long, PuppyGetResponse> pMap = new HashMap<>();
		for (PuppyGetResponse dto : pList) {
			pMap.put(dto.id(), dto);
		}
		return pMap;
	}

	private Puppy getEntity(Long id) {
		return puppyRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.name(), PUPPY.name())));
	}
}
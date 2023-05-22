package com.numble.popularpuppyvote.domain.service;

import static com.numble.popularpuppyvote.common.Message.ENTITY_NOT_FOUND;
import static com.numble.popularpuppyvote.common.Message.PUPPY;
import static com.numble.popularpuppyvote.common.mapper.PuppyMapper.toPuppiesGetResponse;
import static com.numble.popularpuppyvote.common.mapper.PuppyMapper.toPuppyGetResponse;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.numble.popularpuppyvote.domain.dto.request.EnhancedPuppyListGetRequest;
import com.numble.popularpuppyvote.domain.dto.request.PuppyFilteredListGetRequest;
import com.numble.popularpuppyvote.domain.dto.request.PuppyListGetRequest;
import com.numble.popularpuppyvote.domain.dto.request.PuppySortedListGetRequest;
import com.numble.popularpuppyvote.domain.dto.response.PuppyListGetResponse;
import com.numble.popularpuppyvote.domain.dto.response.PuppyGetResponse;
import com.numble.popularpuppyvote.domain.model.Puppy;
import com.numble.popularpuppyvote.domain.repository.PuppyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PuppyReadService {

	private final PuppyRepository puppyRepository;

	@Transactional(readOnly = true)
	public PuppyGetResponse getOnePuppy(Long puppyId) {
		return toPuppyGetResponse(getEntity(puppyId));
	}

	@Transactional(readOnly = true)
	public PuppyListGetResponse getPuppies(PuppyListGetRequest request) {

		List<Puppy> puppies = puppyRepository.findPuppies(request.cursorId(), request.pageSize());
		long lastId = puppies.size() > 0 ? puppies.get(puppies.size() - 1).getId() : -1L;

		return toPuppiesGetResponse(puppies, lastId);
	}

	@Transactional(readOnly = true)
	public PuppyListGetResponse getFilteredPuppies(PuppyFilteredListGetRequest request) {
		List<Puppy> puppies = puppyRepository.findPuppiesWithFiltering(request.cursorId(), request.pageSize(),
				request.species(), request.sizes());
		long lastId = puppies.size() > 0 ? puppies.get(puppies.size() - 1).getId() : -1L;

		return toPuppiesGetResponse(puppies, lastId);
	}

	@Transactional(readOnly = true)
	public PuppyListGetResponse getSortedPuppies(PuppySortedListGetRequest request) {
		List<Puppy> puppies = puppyRepository.findPuppiesWithSorting(request.cursorId(), request.pageSize(),
				request.criteria(), request.isAscending());
		long lastId = puppies.size() > 0 ? puppies.get(puppies.size() - 1).getId() : -1L;

		return toPuppiesGetResponse(puppies, lastId);
	}

	@Transactional(readOnly = true)
	public PuppyListGetResponse enhancedGetPuppies(EnhancedPuppyListGetRequest request) {
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
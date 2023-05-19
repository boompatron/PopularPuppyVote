package com.numble.popularpuppyvote.domain.puppy.service;

import static com.numble.popularpuppyvote.common.Message.ENTITY_NOT_FOUND;
import static com.numble.popularpuppyvote.common.Message.PUPPY;
import static com.numble.popularpuppyvote.common.mapper.PuppyMapper.toPuppy;
import static com.numble.popularpuppyvote.common.mapper.PuppyMapper.toPuppyCreateResponse;
import static com.numble.popularpuppyvote.common.mapper.PuppyMapper.toPuppyUpdateResponse;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.numble.popularpuppyvote.domain.puppy.dto.request.PuppyCreateRequest;
import com.numble.popularpuppyvote.domain.puppy.dto.request.PuppyUpdateRequest;
import com.numble.popularpuppyvote.domain.puppy.dto.response.PuppyCreateResponse;
import com.numble.popularpuppyvote.domain.puppy.dto.response.PuppyUpdateResponse;
import com.numble.popularpuppyvote.domain.puppy.model.Puppy;
import com.numble.popularpuppyvote.domain.puppy.repository.PuppyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PuppyService {
	private final PuppyRepository puppyRepository;

	@Transactional
	public PuppyCreateResponse registerPuppy(PuppyCreateRequest request) {
		return toPuppyCreateResponse(puppyRepository.save(toPuppy(request)));
	}

	@Transactional
	public PuppyUpdateResponse updatePuppy(PuppyUpdateRequest request){
		Puppy puppy = getEntity(request.id());
		if (!request.imageUrl().isEmpty())
			puppy.updateImageUrl(request.imageUrl());
		if (!request.description().isEmpty())
			puppy.updateDescription(request.description());

		return toPuppyUpdateResponse(puppyRepository.save(puppy));
	}

	@Transactional
	public void deletePuppy(Long puppyId){
		puppyRepository.deleteById(puppyId);
	}

	private Puppy getEntity(Long id) {
		return puppyRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.name(), PUPPY.name())));
	}
}

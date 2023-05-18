package com.numble.popularpuppyvote.domain.puppy.service;

import static com.numble.popularpuppyvote.common.Message.ENTITY_NOT_FOUND;
import static com.numble.popularpuppyvote.common.Message.PUPPY;
import static com.numble.popularpuppyvote.common.mapper.PuppyMapper.toPuppiesGetResponse;
import static com.numble.popularpuppyvote.common.mapper.PuppyMapper.toPuppyGetResponse;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.numble.popularpuppyvote.domain.puppy.dto.request.PuppyListGetRequest;
import com.numble.popularpuppyvote.domain.puppy.dto.response.PuppyListGetResponse;
import com.numble.popularpuppyvote.domain.puppy.dto.response.PuppyGetResponse;
import com.numble.popularpuppyvote.domain.puppy.model.Puppy;
import com.numble.popularpuppyvote.domain.puppy.repository.PuppyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PuppyReadService {

	private final PuppyRepository puppyRepository;

	@Transactional(readOnly = true)
	public PuppyGetResponse getOnePuppy(Long puppyId){
		return toPuppyGetResponse(getEntity(puppyId));
	}


	@Transactional(readOnly = true)
	public PuppyListGetResponse getPuppies(PuppyListGetRequest request){

		List<Puppy> puppies = puppyRepository.findPuppies(request.cursorId(), request.pageSize());
		long lastId = puppies.size() > 0 ? puppies.get(puppies.size() - 1).getId() : -1L;

		return toPuppiesGetResponse(puppies, lastId);
	}

	private Puppy getEntity(Long id) {
		return puppyRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.name(), PUPPY.name())));
	}
}
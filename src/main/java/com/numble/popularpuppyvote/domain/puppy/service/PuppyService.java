package com.numble.popularpuppyvote.domain.puppy.service;

import static com.numble.popularpuppyvote.common.mapper.PuppyMapper.toPuppy;
import static com.numble.popularpuppyvote.common.mapper.PuppyMapper.toPuppyCreateResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.numble.popularpuppyvote.domain.puppy.dto.request.PuppyCreateRequest;
import com.numble.popularpuppyvote.domain.puppy.dto.response.PuppyCreateResponse;
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
}

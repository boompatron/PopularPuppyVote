package com.numble.popularpuppyvote.domain.puppy.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.numble.popularpuppyvote.domain.puppy.dto.request.PuppyCreateRequest;
import com.numble.popularpuppyvote.domain.puppy.dto.response.PuppyCreateResponse;
import com.numble.popularpuppyvote.domain.puppy.service.PuppyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/puppies")
@RequiredArgsConstructor
public class PuppyController {

	private final PuppyService puppyService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<PuppyCreateResponse> registerPuppy(
			@RequestBody @Valid PuppyCreateRequest request
	){
		return ResponseEntity.ok(puppyService.registerPuppy(request));
	}

}

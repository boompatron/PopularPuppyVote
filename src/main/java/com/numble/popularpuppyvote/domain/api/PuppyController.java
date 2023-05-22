package com.numble.popularpuppyvote.domain.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.numble.popularpuppyvote.domain.dto.request.PuppyCreateRequest;
import com.numble.popularpuppyvote.domain.dto.request.PuppyFilteredListGetRequest;
import com.numble.popularpuppyvote.domain.dto.request.PuppyListGetRequest;
import com.numble.popularpuppyvote.domain.dto.request.PuppySortedListGetRequest;
import com.numble.popularpuppyvote.domain.dto.request.PuppyUpdateRequest;
import com.numble.popularpuppyvote.domain.dto.response.PuppyCreateResponse;
import com.numble.popularpuppyvote.domain.dto.response.PuppyGetResponse;
import com.numble.popularpuppyvote.domain.dto.response.PuppyListGetResponse;
import com.numble.popularpuppyvote.domain.dto.response.PuppyUpdateResponse;
import com.numble.popularpuppyvote.domain.service.PuppyReadService;
import com.numble.popularpuppyvote.domain.service.PuppyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/puppies")
@RequiredArgsConstructor
public class PuppyController {

	private final PuppyService puppyService;
	private final PuppyReadService puppyReadService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<PuppyCreateResponse> registerPuppy(
			@RequestBody @Valid PuppyCreateRequest request
	) {
		return new ResponseEntity<>(puppyService.registerPuppy(request), HttpStatus.CREATED);
	}

	@GetMapping("/{puppyId}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<PuppyGetResponse> getOnePuppy(
			@PathVariable Long puppyId
	) {
		return ResponseEntity.ok(puppyReadService.getOnePuppy(puppyId));
	}

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<PuppyListGetResponse> getPuppies(
			@Valid PuppyListGetRequest request
	) {
		return ResponseEntity.ok(puppyReadService.getPuppies(request));
	}

	@GetMapping("/filter")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<PuppyListGetResponse> getFilteredPuppies(
			@Valid PuppyFilteredListGetRequest request
	) {
		return ResponseEntity.ok(puppyReadService.getFilteredPuppies(request));
	}

	@GetMapping("/sort")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<PuppyListGetResponse> getSortedPuppies(
			@Valid PuppySortedListGetRequest request
	) {
		return ResponseEntity.ok(puppyReadService.getSortedPuppies(request));
	}

	@PatchMapping
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<PuppyUpdateResponse> updatePuppy(
			@RequestBody PuppyUpdateRequest request
	){
		return ResponseEntity.ok(puppyService.updatePuppy(request));
	}

	@DeleteMapping("/{puppyId}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<Void> deletePuppy(
			@PathVariable Long puppyId
	) {
		puppyService.deletePuppy(puppyId);
		return ResponseEntity.ok().build();
	}
}

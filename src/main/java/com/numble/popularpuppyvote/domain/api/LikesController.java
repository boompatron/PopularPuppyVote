package com.numble.popularpuppyvote.domain.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.numble.popularpuppyvote.domain.dto.request.LikesRegisterRequest;
import com.numble.popularpuppyvote.domain.dto.response.LikesCountGetResponse;
import com.numble.popularpuppyvote.domain.dto.response.LikesRegisterResponse;
import com.numble.popularpuppyvote.domain.service.LikesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/likes")
@RequiredArgsConstructor
public class LikesController {
	private final LikesService likesService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<LikesRegisterResponse> registerLikes(@RequestBody LikesRegisterRequest request) {
		return new ResponseEntity<>(likesService.registerLikes(request), HttpStatus.CREATED);
	}

	@PatchMapping("/{likesId}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<Void> deleteLikes(@PathVariable Long likesId) {
		likesService.deleteLikes(likesId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{puppyId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<LikesCountGetResponse> getLikesByPuppyId(
			@PathVariable Long puppyId
	) {
		return new ResponseEntity<>(likesService.getLikesByPuppyId(puppyId), HttpStatus.OK);
	}

}

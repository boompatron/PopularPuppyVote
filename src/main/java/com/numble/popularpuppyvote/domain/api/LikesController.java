package com.numble.popularpuppyvote.domain.api;

import javax.servlet.http.HttpSession;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/likes")
@RequiredArgsConstructor
@Tag(name = "좋아요 API")
public class LikesController {
	private final LikesService likesService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@Operation(summary = "좋아요 등록")
	public ResponseEntity<LikesRegisterResponse> registerLikes(HttpSession httpSession,
		@RequestBody Long puppyId) {
		if (httpSession.getAttribute(puppyId.toString()) == null) {
			httpSession.setAttribute(puppyId.toString(), puppyId + " already voted with this session");
			return new ResponseEntity<>(
				likesService.registerLikes(new LikesRegisterRequest(puppyId, httpSession.getId())),
				HttpStatus.CREATED);
		}
		return ResponseEntity.badRequest().build();
	}

	@PatchMapping("/{puppyId}")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "좋아요 삭제")
	public ResponseEntity<Void> deleteLikes(HttpSession httpSession, @PathVariable Long puppyId) {
		likesService.deleteLikes(httpSession.getId(), puppyId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{puppyId}")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "좋아요 조회")
	public ResponseEntity<LikesCountGetResponse> getLikesByPuppyId(
		@PathVariable Long puppyId
	) {
		return new ResponseEntity<>(likesService.getLikesByPuppyId(puppyId), HttpStatus.OK);
	}

}

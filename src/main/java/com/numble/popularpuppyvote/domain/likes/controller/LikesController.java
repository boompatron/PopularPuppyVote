package com.numble.popularpuppyvote.domain.likes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.numble.popularpuppyvote.domain.likes.dto.request.LikesRegisterRequest;
import com.numble.popularpuppyvote.domain.likes.dto.response.LikesRegisterResponse;
import com.numble.popularpuppyvote.domain.likes.service.LikesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/likes")
@RequiredArgsConstructor
public class LikesController {
	private final LikesService likesService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<LikesRegisterResponse> registerLikes(@RequestBody LikesRegisterRequest request){
		return new ResponseEntity<>(likesService.registerLikes(request), HttpStatus.CREATED);
	}
}

package com.numble.popularpuppyvote.domain.likes.controller;

import org.springframework.web.bind.annotation.RestController;

import com.numble.popularpuppyvote.domain.likes.service.LikesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LikesController {
	private final LikesService likesService;
}

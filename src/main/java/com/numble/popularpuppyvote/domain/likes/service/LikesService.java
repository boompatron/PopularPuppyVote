package com.numble.popularpuppyvote.domain.likes.service;

import org.springframework.stereotype.Service;

import com.numble.popularpuppyvote.domain.likes.repository.LikesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikesService {
	private final LikesRepository likesRepository;
}

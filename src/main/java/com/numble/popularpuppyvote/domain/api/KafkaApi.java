package com.numble.popularpuppyvote.domain.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.numble.popularpuppyvote.domain.dto.request.LikesRegisterRequest;
import com.numble.popularpuppyvote.domain.service.KafkaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/kafka")
@RequiredArgsConstructor
public class KafkaApi {

	private final KafkaService kafkaService;

	@PostMapping
	public ResponseEntity<Void> publish(
			@RequestBody LikesRegisterRequest request
	) {
		kafkaService.sendRequest(request);
		return ResponseEntity.ok().build();
	}


}

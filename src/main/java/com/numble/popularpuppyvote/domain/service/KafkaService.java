package com.numble.popularpuppyvote.domain.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.numble.popularpuppyvote.domain.dto.request.LikesRegisterRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaService {

	@Value("${message.topic.name}")
	private String TOPIC_NAME;

	private final KafkaTemplate<String, LikesRegisterRequest> kafkaTemplate;

	public void sendRequest(LikesRegisterRequest request){
		kafkaTemplate.send(TOPIC_NAME, request);
	}
}

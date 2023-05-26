package com.numble.popularpuppyvote.domain.usecase;

import static com.numble.popularpuppyvote.common.mapper.LikesMapper.toLikes;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.numble.popularpuppyvote.domain.dto.request.LikesRegisterRequest;
import com.numble.popularpuppyvote.domain.repository.LikesRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {

	private final LikesRepository likesRepository;
	private final ObjectMapper objectMapper;

	@KafkaListener(topics = "ppv_topic")
	public void listenRequest(String requestMessage){
		try {
			LikesRegisterRequest request = objectMapper.readValue(requestMessage, LikesRegisterRequest.class);
			likesRepository.save(toLikes(request));
			log.info("puppy " + request.puppyId() + " got vote via kafka");
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}

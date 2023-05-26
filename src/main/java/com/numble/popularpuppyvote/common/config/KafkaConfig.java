package com.numble.popularpuppyvote.common.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaConfig {

	@Value("${spring.kafka.bootstrap-server}")
	private String bootStrapServerAddress;

	@Bean
	KafkaAdmin kafkaAdmin(){
		Map<String, Object> configs = new HashMap<>();
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServerAddress);

		return new KafkaAdmin(configs);
	}
}

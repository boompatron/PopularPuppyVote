package com.numble.popularpuppyvote.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI openApi() {
		Info info = new Info()
			.title("WuMo")
			.version("v1.0.0")
			.description("강아지 인기투표");

		return new OpenAPI()
			.components(new Components())
			.info(info);
	}
}

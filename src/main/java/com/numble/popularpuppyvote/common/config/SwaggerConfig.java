package com.numble.popularpuppyvote.common.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket apiV1() {
		return new Docket(DocumentationType.SWAGGER_2)
			.consumes(getConsumeContentTypes())
			.produces(getProduceContentTypes())
			.useDefaultResponseMessages(false)
			.groupName("GrapeFlavoredApple 1")
			.select()
			.apis(RequestHandlerSelectors.
				basePackage("com.numble.popularpuppyvote"))
			.paths(PathSelectors.ant("/api/v1/**"))
			.build()
			.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title("Popular Puppy Vote")
			.description("강아지 인기 투표")
			.version("version 1.0")
			.build();
	}

	private Set<String> getConsumeContentTypes() {
		Set<String> consumes = new HashSet<>();
		consumes.add(MediaType.APPLICATION_JSON_VALUE);
		consumes.add(MediaType.MULTIPART_FORM_DATA_VALUE);
		return consumes;
	}

	private Set<String> getProduceContentTypes() {
		Set<String> produces = new HashSet<>();
		produces.add(MediaType.APPLICATION_JSON_VALUE);
		return produces;
	}
}

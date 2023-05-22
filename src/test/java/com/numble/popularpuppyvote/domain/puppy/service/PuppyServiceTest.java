package com.numble.popularpuppyvote.domain.puppy.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.numble.popularpuppyvote.domain.dto.request.PuppyCreateRequest;
import com.numble.popularpuppyvote.domain.dto.response.PuppyCreateResponse;
import com.numble.popularpuppyvote.domain.model.Puppy;
import com.numble.popularpuppyvote.domain.repository.PuppyRepository;
import com.numble.popularpuppyvote.domain.service.PuppyService;

@DisplayName("PuppyServiceTest 에서")
@ExtendWith(MockitoExtension.class)
public class PuppyServiceTest {

	@InjectMocks
	PuppyService puppyService;

	@Mock
	PuppyRepository puppyRepository;

	Puppy puppy;

	@BeforeEach
	void beforeEach() {
		puppy = new Puppy(1L, "coco", "coco.com", "귀여운 코코");
	}

	@Nested
	@DisplayName("registerPuppy를 통해")
	class RegisterPuppy {

		@Test
		@DisplayName("강아지를 등록한다")
		void registerPuppy() {
			// Given
			PuppyCreateRequest request = new PuppyCreateRequest("coco", "coco.com", "귀여운 코코");
			given(puppyRepository.save(any(Puppy.class))).willReturn(puppy);

			// When
			PuppyCreateResponse response = puppyService.registerPuppy(request);

			// Then
			assertThat(response.id()).isEqualTo(1L);
		}
	}
}

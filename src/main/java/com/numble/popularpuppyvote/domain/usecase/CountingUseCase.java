package com.numble.popularpuppyvote.domain.usecase;

import java.util.List;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import com.numble.popularpuppyvote.domain.dto.response.PuppyGetResponse;
import com.numble.popularpuppyvote.domain.repository.LikesRepository;
import com.numble.popularpuppyvote.domain.repository.PuppyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountingUseCase {
	private final LikesRepository likesRepository;
	private final PuppyRepository puppyRepository;
	private final RedisTemplate<String, PuppyGetResponse> redisTemplate;

	private final int fixedDelay = 5 * 60 * 1000;			// 스케쥴링 간격
	private final int initialDelay = 10 * 60 * 1000;		// 어플리케이션 구동 후 스케쥴링 시작 시간

	private static final String REDIS_PUPPY_KEY_PREFIX = "PUPPY";

	@Transactional
	@Scheduled(fixedDelay = fixedDelay, initialDelay = initialDelay)
	public void countLikes(){
		// TODO 집계 로직 최적화하기

		var schedulingWatch = new StopWatch();
		schedulingWatch.start();
		List<Long> ids = puppyRepository.getAllIdsDistinctGroupBy();

		for (Long id: ids){
			String key = REDIS_PUPPY_KEY_PREFIX + id;

			Integer cnt = likesRepository.getLikesCountByPuppyId(id);
			puppyRepository.updateLikeCountById(id, cnt);

			if (redisTemplate.opsForValue().get(key) != null){
				PuppyGetResponse outDatedData = redisTemplate.opsForValue().get(key);
				PuppyGetResponse updatedData = new PuppyGetResponse(
						outDatedData.id(),
						outDatedData.name(),
						outDatedData.imageUrl(),
						outDatedData.description(),
						outDatedData.species(),
						outDatedData.size(),
						cnt
				);

				log.info("강아지 " + id + " 좋아요 수 레디스에 있는 값 갱신 " + outDatedData.likeCount() + " -> " + cnt);
				redisTemplate.opsForValue().set(key, updatedData);
			}

		}
		schedulingWatch.stop();
		log.info("집계 로직 수행 시간 : " + schedulingWatch.getTotalTimeSeconds() + "s");
 	}
}

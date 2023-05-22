package com.numble.popularpuppyvote.domain.facade;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.numble.popularpuppyvote.domain.repository.LikesRepository;
import com.numble.popularpuppyvote.domain.repository.PuppyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CountingUseCase {
	private final LikesRepository likesRepository;
	private final PuppyRepository puppyRepository;

	private final int fixedDelay = 10 * 60 * 1000;			// 스케쥴링 간격
	private final int initialDelay = 5 * 60 * 1000;		// 어플리케이션 구동 후 스케쥴링 시작 시간

	@Transactional
	@Scheduled(fixedDelay = fixedDelay, initialDelay = initialDelay)
	public void countLikes(){
		// TODO 집계 로직 최적화하기
		List<Long> ids = puppyRepository.getAllIdsDistinctGroupBy();

		for (Long id: ids){
			Integer cnt = likesRepository.getLikesCountByPuppyId(id);
			puppyRepository.updateLikeCountById(id, cnt);
		}
 	}
}

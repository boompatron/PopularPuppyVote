package com.numble.popularpuppyvote.domain.likes;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import com.numble.popularpuppyvote.domain.repository.LikesRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class LikesTest {

	@Autowired
	LikesRepository likesRepository;

	@Test
	void test(){
		long cnt = likesRepository.getLikesCountByPuppyId(11L);
		System.out.println("cnt : " + cnt);
	}

	@Test
	void groupByVSDistinct(){

		var distinctWatch = new StopWatch();
		var groupByWatch = new StopWatch();

		distinctWatch.start();
		List<Long> distinctIds = likesRepository.getPuppyIdsDistinct();
		distinctWatch.stop();

		groupByWatch.start();
		List<Long> groupByIds = likesRepository.getPuppyIdsGroupBy();
		groupByWatch.stop();

		log.info("Distinct Time : " + distinctWatch.getTotalTimeMillis() + "ms");
		log.info("Group By Time : " + groupByWatch.getTotalTimeMillis() + "ms");
	}

	@Test
	@Transactional
	void cleanUpTest(){
		LocalDateTime ondDayAgo = LocalDateTime.now().minusDays(1);
		LocalDateTime threeDaysAgo = LocalDateTime.now().minusDays(3);
		// likesRepository.cleanUp(twoDaysAgo.toString());
		System.out.println(ondDayAgo + ", " + threeDaysAgo);
		// likesRepository.cleanUp(ondDayAgo, threeDaysAgo);
	}
}

package com.numble.popularpuppyvote.domain.likes;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import com.numble.popularpuppyvote.domain.repository.LikesRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class likesTest {

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
}

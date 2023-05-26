package com.numble.popularpuppyvote.domain.puppy;

import java.util.List;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import com.numble.popularpuppyvote.domain.repository.PuppyRepository;
import com.numble.popularpuppyvote.domain.utils.PuppyFixtureFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PuppyTest {

	@Autowired
	PuppyRepository puppyRepository;

	@Test
	void timeTest() {
		var groupByWatch = new StopWatch();
		var distinctWatch = new StopWatch();
		var groupByAndDistinctWatch = new StopWatch();

		groupByWatch.start();
		List<Long> groupByIds = puppyRepository.getAllIdsGroupBy();
		groupByWatch.stop();
		log.info("GroupBy Time : " + groupByWatch.getTotalTimeMillis() + "ms");

		distinctWatch.start();
		List<Long> distinctIds = puppyRepository.getAllIdsDistinct();
		distinctWatch.stop();
		log.info("Distinct Time : " + distinctWatch.getTotalTimeMillis() + "ms");

		groupByAndDistinctWatch.start();
		List<Long> mixedIds = puppyRepository.getAllIdsDistinctGroupBy();
		groupByAndDistinctWatch.stop();
		log.info("Mixed Time : " + groupByAndDistinctWatch.getTotalTimeMillis() + "ms");
	}

	@Test
	void puppyBulkInsert() {
		var puppies = LongStream.range(1, 31)
				.parallel()
				.mapToObj(PuppyFixtureFactory::create)
				.toList();

		puppyRepository.saveAll(puppies);
	}
}

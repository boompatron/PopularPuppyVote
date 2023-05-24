package com.numble.popularpuppyvote.domain.likes;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import com.numble.popularpuppyvote.domain.repository.LikesJdbcRepository;
import com.numble.popularpuppyvote.domain.repository.LikesRepository;
import com.numble.popularpuppyvote.domain.utils.LikesFixtureFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class BulkLike {

	@Autowired
	LikesRepository likesRepository;

	@Autowired
	LikesJdbcRepository likesJdbcRepository;

	@Test
	void testTest() {
		LongStream.range(0, 10)
				.mapToObj(i -> LikesFixtureFactory.create(i, 7L))
				.forEach(likes -> System.out.println(
								"ID : " + likes.getId() + ", puppy id : " + likes.getPuppyId() + ", isDeleted : " + likes.getIsDeleted()
						)
				);
	}

	@Test
	void insertLikesBulk() {

		int dateSize = 10 * 10000;
		long puppySize = 30L;

		StopWatch generatingData, bulkInsert;

		for (int i = 0; i < 10; i++) {
			generatingData = new StopWatch();
			bulkInsert = new StopWatch();

			generatingData.start();
			var likes = IntStream.range(0, dateSize)
					.parallel()
					.mapToObj(o -> LikesFixtureFactory.create(o, puppySize))
					.toList();
			generatingData.stop();

			bulkInsert.start();
			likesJdbcRepository.bulkInsert(likes);
			bulkInsert.stop();

			String logString = "#" + (i + 1) + "\t데이터 크기 : " + dateSize + "\t| 데이터 생성에 걸린 시간 : "
					+ Math.round(generatingData.getTotalTimeSeconds() * 1000) / 1000.0
					+ "s\t |  데이터 삽입에 걸린 시간 : " + Math.round(bulkInsert.getTotalTimeSeconds() * 1000) / 1000.0 + "s";

			log.info(logString);
		}
	}
}

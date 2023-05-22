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
		StopWatch generatingData, bulkInsert;

		for (int i = 1; i < 11; i++) {
			generatingData = new StopWatch();
			bulkInsert = new StopWatch();

			generatingData.start();
			var likes = IntStream.range(0, dateSize)
					.parallel()
					.mapToObj(o -> LikesFixtureFactory.create(o, 25L))
					.toList();
			generatingData.stop();

			bulkInsert.start();
			likesJdbcRepository.bulkInsert(likes);
			bulkInsert.stop();

			String logString = String.format("""
					# %d: 데이터 크기 : %d, 데이터 생성에 걸리는 시간 : %.3fs, 데이터 삽입에 걸리는 시간 : %.3fs
					""", i, dateSize, generatingData.getTotalTimeSeconds(), bulkInsert.getTotalTimeSeconds()
			);

			log.info(logString);
		}
	}

	@Test
	void stringTest() {
		double a = 12.123456;
		String logString = String.format("""
				%d, %.4fs
				""", 1, a);
		log.info(logString);
	}
}

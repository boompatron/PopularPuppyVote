package com.numble.popularpuppyvote.domain.likes;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.numble.popularpuppyvote.domain.repository.LikesJdbcRepository;
import com.numble.popularpuppyvote.domain.repository.LikesRepository;
import com.numble.popularpuppyvote.domain.utils.LikesFixtureFactory;


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
				.forEach(likes -> {
					System.out.println("ID : " + likes.getId() + ", puppy id : " + likes.getPuppyId() + ", isDeleted : " + likes.getIsDeleted());
				});
	}

	@Test
	void insertLikesBulk() {

		var likes = IntStream.range(0, 10 * 10000)
				.parallel()
				.mapToObj(i -> LikesFixtureFactory.create(i ,20L))
				.toList();

		likesJdbcRepository.bulkInsert(likes);
		// likesRepository.saveAll(likes);
	}
}

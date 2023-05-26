package com.numble.popularpuppyvote.domain.likes;

import static org.jeasy.random.FieldPredicates.inClass;
import static org.jeasy.random.FieldPredicates.named;
import static org.jeasy.random.FieldPredicates.ofType;

import java.lang.reflect.Field;
import java.util.Random;
import java.util.function.Predicate;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.numble.popularpuppyvote.domain.model.Likes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class BulkTest {

	Likes createLikes(int puppySize) {
		Predicate<Field> idPredicate = named("id")
				.and(ofType(Long.class))
				.and(inClass(Likes.class));

		Predicate<Field> isDeletedPredicate = named("isDeleted")
				.and(ofType(Boolean.class))
				.and(inClass(Likes.class));

		Predicate<Field> puppyIdPredicate = named("puppyId")
				.and(ofType(Boolean.class))
				.and(inClass(Likes.class));

		EasyRandomParameters parameters = new EasyRandomParameters()
				.excludeField(idPredicate)
				.randomize(isDeletedPredicate, () -> false)
				.randomize(puppyIdPredicate, () -> new Random().nextLong(1, puppySize + 1));

		return new EasyRandom(parameters).nextObject(Likes.class);
	}

	@Test
	void easyRandomTest() {
		int puppySize = 10;
		Likes likes = createLikes(puppySize);
		log.info(likes.toString());
	}
}

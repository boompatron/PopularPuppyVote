package com.numble.popularpuppyvote.domain.utils;

import static org.jeasy.random.FieldPredicates.inClass;
import static org.jeasy.random.FieldPredicates.named;
import static org.jeasy.random.FieldPredicates.ofType;

import java.time.LocalDate;
import java.util.Random;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import com.numble.popularpuppyvote.domain.model.Likes;

public class LikesFixtureFactory {

	public static Likes create() {
		var params = new EasyRandomParameters();

		return new EasyRandom(params).nextObject(Likes.class);
	}

	public static Likes create(Long seed, Long puppySize) {
		var idPredicate = named("id")
				.and(ofType(Long.class))
				.and(inClass(Likes.class));

		var isDeletedPredicate = named("isDeleted")
				.and(ofType(Boolean.class))
				.and(inClass(Likes.class));

		var params = new EasyRandomParameters()
				.seed(seed)
				.dateRange(LocalDate.of(2022, 10, 1), LocalDate.of(2023, 5, 10))
				.randomize(idPredicate, () -> seed)
				.randomize(isDeletedPredicate, () -> false)
				.randomize(Long.class, () -> new Random().nextLong(0, puppySize));
				;

		return new EasyRandom(params).nextObject(Likes.class);
	}

	public static Likes create(int seed, Long puppySize) {
		var idPredicate = named("id")
				.and(ofType(Long.class))
				.and(inClass(Likes.class));

		var isDeletedPredicate = named("isDeleted")
				.and(ofType(Boolean.class))
				.and(inClass(Likes.class));

		var params = new EasyRandomParameters()
				.seed(seed)
				.dateRange(LocalDate.of(2022, 10, 1), LocalDate.of(2023, 5, 10))
				.randomize(idPredicate, () -> (long)seed)
				.randomize(isDeletedPredicate, () -> false)
				.randomize(Long.class, () -> new Random().nextLong(0, puppySize));
				;

		return new EasyRandom(params).nextObject(Likes.class);
	}
}

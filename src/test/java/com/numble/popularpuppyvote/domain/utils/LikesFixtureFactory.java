package com.numble.popularpuppyvote.domain.utils;

import static org.jeasy.random.FieldPredicates.inClass;
import static org.jeasy.random.FieldPredicates.named;
import static org.jeasy.random.FieldPredicates.ofType;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.function.Predicate;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import com.numble.popularpuppyvote.domain.model.Likes;

public class LikesFixtureFactory {

	public static Likes create() {
		var params = new EasyRandomParameters();

		return new EasyRandom(params).nextObject(Likes.class);
	}

	// public static Likes create(Long seed, Long puppySize) {
	// 	var idPredicate = named("id")
	// 		.and(ofType(Long.class))
	// 		.and(inClass(Likes.class));
	//
	// 	var isDeletedPredicate = named("isDeleted")
	// 		.and(ofType(Boolean.class))
	// 		.and(inClass(Likes.class));
	//
	// 	var params = new EasyRandomParameters()
	// 		.seed(seed)
	// 		.stringLengthRange(11, 11)
	// 		.randomize(idPredicate, () -> seed)
	// 		.randomize(isDeletedPredicate, () -> false)
	// 		.randomize(Long.class, () -> new Random().nextLong(0, puppySize));
	//
	// 	return new EasyRandom(params).nextObject(Likes.class);
	// }

	public static Likes create(int seed, Long puppySize) {
		Predicate<Field> idPredicate = named("id")
			.and(ofType(Long.class))
			.and(inClass(Likes.class));

		Predicate<Field> isDeletedPredicate = named("isDeleted")
			.and(ofType(Boolean.class))
			.and(inClass(Likes.class));

		Predicate<Field> createdAtPredicate = named("createdAt")
			.and(ofType(LocalDateTime.class))
			.and(inClass(Likes.class));

		Predicate<Field> updatedAtPredicate = named("updatedAt")
			.and(ofType(LocalDateTime.class))
			.and(inClass(Likes.class));

		EasyRandomParameters params = new EasyRandomParameters()
			.seed(seed)
			.stringLengthRange(11, 11)
			.excludeField(createdAtPredicate)
			.excludeField(updatedAtPredicate)
			.excludeField(idPredicate)
			.randomize(isDeletedPredicate, () -> false)
			.randomize(Long.class, () -> new Random().nextLong(1, puppySize + 1));

		return new EasyRandom(params).nextObject(Likes.class);
	}
}

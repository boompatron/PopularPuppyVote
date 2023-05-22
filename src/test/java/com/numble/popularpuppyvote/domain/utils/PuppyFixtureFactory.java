package com.numble.popularpuppyvote.domain.utils;

import static org.jeasy.random.FieldPredicates.inClass;
import static org.jeasy.random.FieldPredicates.named;
import static org.jeasy.random.FieldPredicates.ofType;

import java.time.LocalDate;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import com.numble.popularpuppyvote.domain.model.Puppy;

public class PuppyFixtureFactory {

	public static Puppy create(Long seed) {
		var idPredicate = named("id")
				.and(ofType(Long.class))
				.and(inClass(Puppy.class));

		var likeCountPredicate = named("likeCount")
				.and(ofType(Integer.class))
				.and(inClass(Puppy.class));

		var params = new EasyRandomParameters()
				.seed(seed)
				.stringLengthRange(2, 10)
				.dateRange(LocalDate.of(2022, 11, 10), LocalDate.of(2023, 5, 10))
				.randomize(idPredicate, () -> seed)
				.randomize(likeCountPredicate, () -> 0);

		return new EasyRandom(params).nextObject(Puppy.class);
	}
}

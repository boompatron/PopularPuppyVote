package com.numble.popularpuppyvote.domain.puppy.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.numble.popularpuppyvote.domain.puppy.model.Puppy;
import com.numble.popularpuppyvote.domain.puppy.model.QPuppy;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PuppyCustomRepositoryImpl implements PuppyCustomRepository {

	private final JPAQueryFactory jpaQueryFactory;
	private final QPuppy qPuppy = QPuppy.puppy;

	@Override
	public List<Puppy> findPuppies(Long cursorId, int pageSize) {
		return jpaQueryFactory
				.selectFrom(qPuppy)
				.where(
						gtPuppyId(cursorId)
				)
				.orderBy(qPuppy.id.asc())
				.limit(pageSize)
				.fetch();
	}

	private BooleanExpression gtPuppyId(Long cursorId) {
		return (cursorId != null) ? qPuppy.id.gt(cursorId) : null;
	}
}

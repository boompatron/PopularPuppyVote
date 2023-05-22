package com.numble.popularpuppyvote.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.numble.popularpuppyvote.domain.model.Puppy;
import com.numble.popularpuppyvote.domain.model.QPuppy;
import com.numble.popularpuppyvote.domain.model.Size;
import com.numble.popularpuppyvote.domain.model.Species;
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

	@Override
	public List<Puppy> findPuppiesWithFiltering(Long cursorId, int pageSize, List<Species> species, List<Size> sizes) {
		return jpaQueryFactory
				.selectFrom(qPuppy)
				.where(
						gtPuppyId(cursorId),
						inSpecies(qPuppy, species),
						inSize(qPuppy, sizes)
				)
				.orderBy(qPuppy.id.asc())
				.limit(pageSize)
				.fetch();
	}

	private BooleanExpression gtPuppyId(Long cursorId) {
		return (cursorId != null) ? qPuppy.id.gt(cursorId) : null;
	}

	private BooleanExpression inSpecies(QPuppy qPuppy, List<Species> species){
		return species.size() > 0 ? qPuppy.species.in(species) : null;
	}

	private BooleanExpression inSize(QPuppy qPuppy, List<Size> sizes) {
		return sizes.size() > 0 ? qPuppy.size.in(sizes) : null;
	}
}

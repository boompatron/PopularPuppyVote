package com.numble.popularpuppyvote.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.numble.popularpuppyvote.domain.model.Puppy;
import com.numble.popularpuppyvote.domain.model.QPuppy;
import com.numble.popularpuppyvote.domain.model.Size;
import com.numble.popularpuppyvote.domain.model.SortingCriteria;
import com.numble.popularpuppyvote.domain.model.Species;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
						inSpecies(species),
						inSize(sizes)
				)
				.orderBy(qPuppy.id.asc())
				.limit(pageSize)
				.fetch();
	}

	@Override
	public List<Puppy> findPuppiesWithSorting(Long cursorId, int pageSize, SortingCriteria criteria,
			Boolean isAscending) {
		return jpaQueryFactory
				.selectFrom(qPuppy)
				.where(
						compare(criteria, cursorId, isAscending)
				)
				.orderBy(
						criteria(criteria, isAscending)
				)
				.limit(pageSize)
				.fetch();
	}

	@Override
	public List<Puppy> findPuppies(Long cursorId, int pageSize,
			List<Species> species, List<Size> sizes,
			SortingCriteria criteria, Boolean isAscending) {
		return jpaQueryFactory
				.selectFrom(qPuppy)
				.where(
						compare(criteria, cursorId, isAscending),
						inSpecies(species),
						inSize(sizes)
				)
				.orderBy(
						criteria(criteria, isAscending)
				)
				.limit(pageSize)
				.fetch();
	}

	@Override
	public List<Puppy> findPuppiesByIds(List<Long> ids) {
		return jpaQueryFactory
			.selectFrom(qPuppy)
			.where(
				qPuppy.id.in(ids)
			)
			.fetch();
	}

	@Override
	public Puppy findByIdCustom(Long id) {
		return jpaQueryFactory
			.selectFrom(qPuppy)
			.where(
				qPuppy.id.eq(id)
			)
			.fetchFirst();
	}

	private BooleanExpression gtPuppyId(Long cursorId) {
		return (cursorId != null) ? qPuppy.id.gt(cursorId) : null;
	}

	private BooleanExpression inSpecies(List<Species> species) {
		return species.size() > 0 ? qPuppy.species.in(species) : null;
	}

	private BooleanExpression inSize(List<Size> sizes) {
		return sizes.size() > 0 ? qPuppy.size.in(sizes) : null;
	}

	private OrderSpecifier criteria(SortingCriteria criteria, Boolean isAscending) {
		return switch (criteria) {
			case DEFAULT -> isAscending ? qPuppy.id.asc() : qPuppy.id.desc();
			case LIKES -> isAscending ? qPuppy.likeCount.asc() : qPuppy.likeCount.desc();
			case SPECIES -> isAscending ? qPuppy.species.asc() : qPuppy.species.desc();
		};
	}

	private BooleanExpression compareByPuppyId(Long cursorId, Boolean isAscending) {
		return isAscending ? qPuppy.id.gt(cursorId) : qPuppy.id.lt(cursorId);
	}

	private BooleanExpression compareByLikeCount(Long cursorId, Boolean isAscending) {
		Integer likeCount =
				jpaQueryFactory
						.select(qPuppy.likeCount)
						.from(qPuppy)
						.where(qPuppy.id.eq(cursorId))
						.fetchOne();
		return isAscending ? qPuppy.likeCount.gt(likeCount) : qPuppy.likeCount.lt(likeCount);
	}

	private BooleanExpression compareBySpecies(Long cursorId, Boolean isAscending) {
		Species species =
				jpaQueryFactory
						.select(qPuppy.species)
						.from(qPuppy)
						.where(qPuppy.id.eq(cursorId))
						.fetchOne();
		return isAscending ? qPuppy.species.gt(species) : qPuppy.species.lt(species);
	}

	// TODO 현재는 비교 할 때 쿼리를 하나 더 날리는데, Species, Size에 숫자로 대체해서 lastId를 id가 아닌 id, 좋아요수, enum에서의 위치 등을 저장하는 방식으로 변경(숫자로 통일해서 문제 해결)
	private BooleanExpression compare(SortingCriteria criteria, Long cursorId, Boolean isAscending) {
		return cursorId != null ? switch (criteria) {
			case DEFAULT -> compareByPuppyId(cursorId, isAscending);
			case LIKES -> compareByLikeCount(cursorId, isAscending);
			case SPECIES -> compareBySpecies(cursorId, isAscending);
		} : null;

	}
}

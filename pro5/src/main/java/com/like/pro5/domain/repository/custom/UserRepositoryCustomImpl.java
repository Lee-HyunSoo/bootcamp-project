package com.like.pro5.domain.repository.custom;


import com.like.pro5.controller.dto.querydsl.ItemSearchCondition;
import com.like.pro5.controller.dto.querydsl.QUserLectureDto;
import com.like.pro5.controller.dto.querydsl.UserLectureDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;

import java.util.List;

import static com.like.pro5.domain.entity.QUser.user;
import static com.like.pro5.domain.entity.item.QLecture.lecture;
import static org.springframework.util.StringUtils.*;

public class UserRepositoryCustomImpl implements UserRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public UserRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<UserLectureDto> searchByKeyword(ItemSearchCondition itemSearchCondition, Pageable pageable) {
//        QueryResults<UserLectureDto> results = queryFactory
//                .select(new QUserLectureDto(
//                        lecture.id.as("itemId"),
//                        lecture.title,
//                        lecture.content,
//                        lecture.category,
//                        user.id.as("userId")
//                ))
//                .from(lecture)
//                .leftJoin(lecture.user, user)
//                .where(
//                        titleEq(itemSearchCondition.getTitle()),
//                        contentEq(itemSearchCondition.getContent())
//                )
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetchResults();
//
//        List<UserLectureDto> lectureContent = results.getResults();
//        long total = results.getTotal();
//        return new PageImpl<>(lectureContent, pageable, total);

        List<UserLectureDto> lectureContent = queryFactory
                .select(new QUserLectureDto(
                        lecture.id.as("itemId"),
                        lecture.title,
                        lecture.content,
                        lecture.category,
                        user.id.as("userId")
                ))
                .from(lecture)
                .leftJoin(lecture.user, user)
                .where(
                        usernameEq(itemSearchCondition.getUsername()),
                        titleEq(itemSearchCondition.getTitle()),
                        contentEq(itemSearchCondition.getContent())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(lecture.count())
                .from(lecture)
                .leftJoin(lecture.user, user)
                .where(
                        usernameEq(itemSearchCondition.getUsername()),
                        titleEq(itemSearchCondition.getTitle()),
                        contentEq(itemSearchCondition.getContent())
                );

        return PageableExecutionUtils.getPage(lectureContent, pageable,  () -> (long) countQuery.fetch().size());
    }

    private BooleanExpression usernameEq(String username) {
        return hasText(username) ? lecture.user.username.eq(username) : null;
    }
    private BooleanExpression contentEq(String content) {
        return hasText(content) ? lecture.content.contains(content) : null;
    }

    private BooleanExpression titleEq(String title) {
        return hasText(title) ? lecture.title.contains(title) : null;
    }
}
package com.like.pro5.domain.repository.custom;

import com.like.pro5.controller.dto.*;
import com.like.pro5.controller.dto.querydsl.ItemSearchCondition;
import com.like.pro5.controller.dto.querydsl.QnaSearchCondition;
import com.like.pro5.controller.dto.querydsl.UserSearchCondition;
import com.like.pro5.domain.entity.QUser;
import com.like.pro5.domain.entity.item.Category;
import com.like.pro5.domain.entity.item.QItem;
import com.like.pro5.domain.entity.post.QQna;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.like.pro5.domain.entity.QUser.*;
import static com.like.pro5.domain.entity.item.QItem.*;
import static com.like.pro5.domain.entity.item.QLecture.lecture;
import static com.like.pro5.domain.entity.post.QQna.qna;
import static org.springframework.util.StringUtils.hasText;


public class AdminRepositoryCustomImpl implements AdminRepositoryCustom {
    private JPAQueryFactory queryFactory;

    public AdminRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<UserDto> findAllUsers(UserSearchCondition userSearchCondition, Pageable pageable) {
        List<UserDto> result = queryFactory
                .select(new QUserDto(
                        user.username,
                        user.password,
                        user.name,
                        user.email,
                        user.tel,
                        user.address.city,
                        user.address.street,
                        user.address.zipcode
                ))
                .from(user)
                .where(
                        userUsernameEq(userSearchCondition.getUsername()),
                        userNameEq(userSearchCondition.getName()),
                        userEmailEq(userSearchCondition.getEmail())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(user.createDate.desc())
                .fetch();

        JPAQuery<Long> count = queryFactory
                .select(user.count())
                .from(user)
                .where(
                        userUsernameEq(userSearchCondition.getUsername()),
                        userNameEq(userSearchCondition.getName()),
                        userEmailEq(userSearchCondition.getEmail())
                );

        return PageableExecutionUtils.getPage(result, pageable, () -> (long) count.fetch().size());
    }

    @Override
    public Page<ItemDto> findAllItems(ItemSearchCondition itemSearchCondition, Pageable pageable) {
        List<ItemDto> result = queryFactory
                .select(new QItemDto(
                        lecture.id,
                        lecture.title,
                        lecture.content,
                        lecture.category,
                        lecture.thumbnailFileName
                ))
                .from(lecture)
                .where(
                        lectureCategoryEq(itemSearchCondition.getCategory()),
                        lectureTitleEq(itemSearchCondition.getTitle()),
                        lectureContentEq(itemSearchCondition.getContent())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(lecture.createDate.desc())
                .fetch();

        JPAQuery<Long> count = queryFactory
                .select(lecture.count())
                .from(lecture)
                .where(
                        lectureCategoryEq(itemSearchCondition.getCategory()),
                        lectureTitleEq(itemSearchCondition.getTitle()),
                        lectureContentEq(itemSearchCondition.getContent())
                );

        return PageableExecutionUtils.getPage(result, pageable, () -> (long) count.fetch().size());
    }

    @Override
    public Page<QnaDto> findAllQnas(QnaSearchCondition qnaSearchCondition, Pageable pageable) {
        List<QnaDto> result = queryFactory
                .select(new QQnaDto(
                        qna.title,
                        qna.content
                ))
                .from(qna)
                .where(
                        qnaContentEq(qnaSearchCondition.getContent()),
                        qnaTitleEq(qnaSearchCondition.getTitle())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qna.createDate.desc())
                .fetch();

        JPAQuery<Long> count = queryFactory
                .select(qna.count())
                .from(qna)
                .where(
                        qnaContentEq(qnaSearchCondition.getContent()),
                        qnaTitleEq(qnaSearchCondition.getTitle())
                );

        return PageableExecutionUtils.getPage(result, pageable, () -> (long) count.fetch().size());
    }

    private BooleanExpression userUsernameEq(String username) {
        return hasText(username) ? user.username.contains(username) : null;
    }

    private BooleanExpression userNameEq(String name) {
        return hasText(name) ? user.name.contains(name) : null;
    }

    private BooleanExpression userEmailEq(String email) {
        return hasText(email) ? user.email.contains(email) : null;
    }

    private BooleanExpression lectureCategoryEq(Category category) {
        return !category.equals(Category.NONE) ? lecture.category.eq(category) : null;
    }

    private BooleanExpression lectureContentEq(String content) {
        return hasText(content) ? lecture.content.contains(content) : null;
    }

    private BooleanExpression lectureTitleEq(String title) {
        return hasText(title) ? lecture.title.contains(title) : null;
    }

    private BooleanExpression qnaContentEq(String content) {
        return hasText(content) ? qna.content.contains(content) : null;
    }

    private BooleanExpression qnaTitleEq(String title) {
        return hasText(title) ? qna.title.contains(title) : null;
    }
}

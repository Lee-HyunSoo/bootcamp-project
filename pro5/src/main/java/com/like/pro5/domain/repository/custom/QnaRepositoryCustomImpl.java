package com.like.pro5.domain.repository.custom;

import static com.like.pro5.domain.entity.post.QQna.qna;
import static org.springframework.util.StringUtils.hasText;

import com.like.pro5.controller.dto.QQnaDto;
import com.like.pro5.controller.dto.QnaDto;
import com.like.pro5.controller.dto.querydsl.QnaSearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class QnaRepositoryCustomImpl implements QnaRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public QnaRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public Page<QnaDto> searchByKeyword(QnaSearchCondition qnaSearchCondition, Pageable pageable) {
        List<QnaDto> qnaContent = queryFactory
                .select(new QQnaDto(
                        qna.title,
                        qna.content
                ))
                .from(qna)
                .where(
                        titleEq(qnaSearchCondition.getTitle()),
                        contentEq(qnaSearchCondition.getContent())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(qna.count())
                .from(qna)
                .where(
                        titleEq(qnaSearchCondition.getTitle()),
                        contentEq(qnaSearchCondition.getContent())
                );

        return PageableExecutionUtils.getPage(qnaContent, pageable, () -> (long) countQuery.fetch().size());
    }

    private BooleanExpression contentEq(String content) {
        return hasText(content) ? qna.content.contains(content) : null;
    }

    private BooleanExpression titleEq(String title) {
        return hasText(title) ? qna.title.contains(title) : null;
    }
}

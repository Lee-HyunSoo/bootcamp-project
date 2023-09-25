package com.like.pro5.domain.repository.custom;

import com.like.pro5.controller.dto.ItemDto;
import com.like.pro5.controller.dto.querydsl.ItemSearchCondition;
import com.like.pro5.controller.dto.QItemDto;
import com.like.pro5.domain.entity.item.Category;
import com.like.pro5.util.trace.TraceStatus;
import com.like.pro5.util.trace.log.ThreadLocalLogTrace;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.like.pro5.domain.entity.item.QLecture.lecture;
import static org.springframework.util.StringUtils.hasText;

@Slf4j
public class ItemRepositoryCustomImpl implements ItemRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    private final ThreadLocalLogTrace trace;

    public ItemRepositoryCustomImpl(EntityManager em, ThreadLocalLogTrace trace) {
        this.queryFactory = new JPAQueryFactory(em);
        this.trace = trace;
    }


    @Override
    public Page<ItemDto> searchByCondition(ItemSearchCondition itemSearchCondition, Pageable pageable) {
        TraceStatus status = trace.begin("ItemRepositoryCustomImpl.searchByCategory()");
        List<ItemDto> items = queryFactory
                .select(new QItemDto(
                        lecture.id,
                        lecture.title,
                        lecture.content,
                        lecture.category,
                        lecture.thumbnailFileName
                ))
                .from(lecture)
                .where(
                        categoryEq(itemSearchCondition.getCategory()),
                        titleEq(itemSearchCondition.getTitle()),
                        contentEq(itemSearchCondition.getContent())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        log.info("categoryEq(itemSearchCondition.getCategory()) : {}",  categoryEq(itemSearchCondition.getCategory()));

        JPAQuery<Long> countQuery = queryFactory
                .select(lecture.count())
                .from(lecture)
                .where(lecture.category.eq(itemSearchCondition.getCategory()));
        trace.end(status);
        return PageableExecutionUtils.getPage(items, pageable, () -> (long) countQuery.fetch().size());
    }

    @Override
    public int lectureTotalPage(ItemSearchCondition itemSearchCondition, Pageable pageable) {
        TraceStatus status = trace.begin("ItemRepositoryCustomImpl.searchByCategory()");
        int totalSize = queryFactory
                .selectFrom(lecture)
                .where(
                        categoryEq(itemSearchCondition.getCategory()),
                        titleEq(itemSearchCondition.getTitle()),
                        contentEq(itemSearchCondition.getContent())
                )
                .fetch().size();
        int pageSize = pageable.getPageSize();
        trace.end(status);
        return totalSize % pageable.getPageSize() == 0 ? totalSize / pageSize : (totalSize / pageSize) + 1;
    }



//    @Override
//    public Page<ItemDto> searchByItemKeyword(ItemSearchCondition itemSearchCondition, Pageable pageable) {
//        List<ItemDto> itemLists = queryFactory
//                .select(new QItemDto(
//                        lecture.title,
//                        lecture.content,
//                        lecture.category,
//                        lecture.thumbnailFileName
//                ))
//                .from(lecture)
//                .where(
//                        titleEq(itemSearchCondition.getTitle()),
//                        contentEq(itemSearchCondition.getContent())
//                )
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//
//        JPAQuery<Long> countQuery = queryFactory
//                .select(lecture.count())
//                .from(lecture)
//                .where(
//                        titleEq(itemSearchCondition.getTitle()),
//                        contentEq(itemSearchCondition.getContent())
//                );
//
//        return PageableExecutionUtils.getPage(itemLists, pageable,  () -> (long) countQuery.fetch().size());
//    }

    private BooleanExpression categoryEq(Category category) {
        return !category.equals(Category.NONE) ? lecture.category.eq(category) : null;
    }

    private BooleanExpression contentEq(String content) {
        return hasText(content) ? lecture.content.contains(content) : null;
    }

    private BooleanExpression titleEq(String title) {
        return hasText(title) ? lecture.title.contains(title) : null;
    }
}

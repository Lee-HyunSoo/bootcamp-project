package com.like.pro5.domain.repository.custom;

import com.like.pro5.controller.dto.QnaDto;
import com.like.pro5.controller.dto.querydsl.QnaSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QnaRepositoryCustom {

//    Page<QnaDto> findQnaByCondition(QnaSearchCondition qnaSearchCondition, Pageable pageable);
    Page<QnaDto> searchByKeyword(QnaSearchCondition qnaSearchCondition, Pageable pageable);
}

package com.like.pro5.domain.repository.custom;

import com.like.pro5.controller.dto.querydsl.ItemSearchCondition;
import com.like.pro5.controller.dto.querydsl.UserLectureDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepositoryCustom {

    Page<UserLectureDto> searchByKeyword(ItemSearchCondition itemSearchCondition, Pageable pageable);


}

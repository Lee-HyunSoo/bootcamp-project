package com.like.pro5.domain.repository.custom;

import com.like.pro5.controller.dto.*;
import com.like.pro5.controller.dto.querydsl.ItemSearchCondition;
import com.like.pro5.controller.dto.querydsl.QnaSearchCondition;
import com.like.pro5.controller.dto.querydsl.UserSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminRepositoryCustom {

    Page<UserDto> findAllUsers(UserSearchCondition userSearchCondition, Pageable pageable);
    Page<ItemDto> findAllItems(ItemSearchCondition itemSearchCondition, Pageable pageable);
    Page<QnaDto> findAllQnas(QnaSearchCondition qnaSearchCondition, Pageable pageable);

}

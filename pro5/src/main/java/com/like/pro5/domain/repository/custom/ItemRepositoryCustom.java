package com.like.pro5.domain.repository.custom;

import com.like.pro5.controller.dto.ItemDto;
import com.like.pro5.controller.dto.querydsl.ItemSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {
    Page<ItemDto> searchByCondition(ItemSearchCondition itemSearchCondition, Pageable pageable);

    int lectureTotalPage(ItemSearchCondition itemSearchCondition, Pageable pageable);
//    Page<ItemDto> searchByItemKeyword(ItemSearchCondition itemSearchCondition, Pageable pageable);
}

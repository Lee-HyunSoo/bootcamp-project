package com.like.pro5.controller.dto.querydsl;

import com.like.pro5.domain.entity.item.Category;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserLectureDto {

    private Long itemId;
    private String title;
    private String content;
    private Category category; // [JAVA, C, PYTHON]
    private Long userId;

    @QueryProjection
    public UserLectureDto(Long itemId, String title, String content, Category category, Long userId) {
        this.itemId = itemId;
        this.title = title;
        this.content = content;
        this.category = category;
        this.userId = userId;
    }
}

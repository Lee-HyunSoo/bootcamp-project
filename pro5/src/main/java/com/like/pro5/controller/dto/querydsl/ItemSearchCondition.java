package com.like.pro5.controller.dto.querydsl;

import com.like.pro5.domain.entity.item.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemSearchCondition {

    private String title;
    private String content;
    private String username;
    private Category category = Category.valueOf("NONE");
    private String thumbnailFileName;

}

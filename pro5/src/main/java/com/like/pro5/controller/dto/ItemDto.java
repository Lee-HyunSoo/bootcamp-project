package com.like.pro5.controller.dto;

import com.like.pro5.domain.entity.item.Category;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

    private Long id;
    private String title;
    private String content;
    private int price;
    private int quantity;
    private Category category;
    private String videoFileName;
    private String thumbnailFileName;
    private String author;
    private String press;

    public ItemDto(String title, int price, int quantity) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    @QueryProjection
    public ItemDto(Long id, String title, String content, Category category, String thumbnailFileName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.thumbnailFileName = thumbnailFileName;
    }

}
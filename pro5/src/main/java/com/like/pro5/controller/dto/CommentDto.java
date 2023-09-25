package com.like.pro5.controller.dto;

import com.like.pro5.domain.entity.post.Comment;
import com.like.pro5.domain.entity.post.QnaStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Long qnaId;
    private String content;
    private String imageFileName;

}

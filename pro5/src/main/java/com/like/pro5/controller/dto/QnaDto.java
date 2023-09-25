package com.like.pro5.controller.dto;

import com.like.pro5.domain.entity.post.Qna;
import com.like.pro5.domain.entity.post.QnaStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QnaDto {

    private Long qnaId;
    private String title;
    private String content;
    private String imageFileName;

    @QueryProjection
    public QnaDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public QnaDto fromQna(Qna qna) {
        this.qnaId = qna.getId();
        this.title = qna.getTitle();
        this.content = qna.getContent();
        this.imageFileName = qna.getImageFileName();

        return this;
    }

}

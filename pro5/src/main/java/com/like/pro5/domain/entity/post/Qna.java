package com.like.pro5.domain.entity.post;

import com.like.pro5.controller.dto.QnaDto;
import com.like.pro5.domain.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue(value = "qna")
@Entity
public class Qna extends Post {

    private String title;

    // 연관관계 메서드
    public void addQnaAndUser(User user) {
        this.setUser(user);
        getUser().getPosts().add(this);
    }
    // 생성 메서드
    public static Qna createUserQna(User user, QnaDto qnaDto) {
        Qna qna = new Qna();
        qna.setUser(user);
        qna.setTitle(qnaDto.getTitle());
        qna.setContent(qnaDto.getContent());
        qna.setImageFileName(qnaDto.getImageFileName());
        return qna;
    }

//    public static Qna createUserQna(QnaDto qnaDto) {
//        Qna qna = new Qna();
//        qna.setTitle(qnaDto.getTitle());
//        qna.setContent(qnaDto.getContent());
//        qna.setImageFileName(qnaDto.getImageFileName());
//        qna.setStatus(QnaStatus.USER);
//
//        return qna;
//    }

}

package com.like.pro5.domain.entity.post;

import com.like.pro5.controller.dto.CommentDto;
import com.like.pro5.domain.entity.Admin;
import com.like.pro5.domain.entity.Order;
import com.like.pro5.domain.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue(value = "comments")
@Entity
public class Comment extends Post {

    private Long qnaId;

    @Enumerated(EnumType.STRING)
    private QnaStatus status; // [ADMIN, USER]

    // == 연관관계 메서드 ==
    public void addCommentByUser(User user) {
        this.setUser(user);
        getUser().getPosts().add(this);
    }

    public void addCommentByAdmin(Admin admin) {
        this.setAdmin(admin);
        getAdmin().getPosts().add(this);
    }


    // == 생성 메서드 ==
//    public static Comment createAdminComment(User user, CommentDto commentDto) {
//        Comment comment = new Comment();
//        comment.setUser(user);
//        comment.setContent(commentDto.getContent());
//        comment.setImageFileName(commentDto.getImageFileName());
//        comment.setStatus(QnaStatus.ADMIN);
//
//        return comment;
//    }

    public static Comment createComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setQnaId(commentDto.getQnaId());
        comment.setContent(commentDto.getContent());
        comment.setImageFileName(commentDto.getImageFileName());
        return comment;
    }

}
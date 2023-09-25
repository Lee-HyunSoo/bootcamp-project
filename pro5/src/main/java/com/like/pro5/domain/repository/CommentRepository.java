package com.like.pro5.domain.repository;

import com.like.pro5.controller.dto.CommentDto;
import com.like.pro5.domain.entity.post.Comment;
import com.like.pro5.domain.repository.custom.QnaRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long>, QnaRepositoryCustom {

    List<Comment> findByQnaId(@Param("qnaId") Long qnaId);

    @Query("select c from Comment c left join fetch c.user where c.user.username = :username")
    List<Comment> findAllByUsername(@Param("username") String username);
}
package com.like.pro5.domain.repository;

import com.like.pro5.domain.entity.post.Qna;
import com.like.pro5.domain.repository.custom.QnaRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaRepository extends JpaRepository<Qna, Long>, QnaRepositoryCustom {

    Qna findByTitle(String title);
}
package com.like.pro5.service;

import com.like.pro5.controller.dto.CommentDto;
import com.like.pro5.domain.entity.Admin;
import com.like.pro5.domain.entity.Role;
import com.like.pro5.domain.entity.User;
import com.like.pro5.domain.entity.post.Comment;
import com.like.pro5.domain.entity.post.Post;
import com.like.pro5.domain.entity.post.Qna;
import com.like.pro5.domain.repository.*;
import com.like.pro5.security.custom.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.events.CommentEvent;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CommentService {

    private final QnaRepository qnaRepository;
    private final CommentRepository commentRepository;
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    /*
     * QnA 답글 작성
     */
    @Transactional
    public Comment createComment(CustomUserDetails userDetails, String originalFilename, CommentDto commentDto) {
        commentDto.setImageFileName(originalFilename);
        Comment comment = Comment.createComment(commentDto);

        if (userDetails.getRole().equals(Role.USER)) {
            User user = userRepository.findByUsername(userDetails.getUsername())
                    .orElseThrow(RuntimeException::new);
            comment.addCommentByUser(user);
        } else {
            Admin admin = adminRepository.findByUsername(userDetails.getUsername())
                    .orElseThrow(RuntimeException::new);
            comment.addCommentByAdmin(admin);
        }

        return commentRepository.save(comment);
    }

    /*
     * 답글 목록
     */
    public List<CommentDto> findAllComment(CustomUserDetails userDetails) {
        List<Comment> comments;
        if(userDetails.getRole().equals(Role.USER)) {
            comments = commentRepository.findAllByUsername(userDetails.getUsername());
        } else {
            comments = commentRepository.findAll();
        }
        return comments.stream().map(
                com -> new CommentDto(com.getId(), com.getContent(), com.getImageFileName())
        ).collect(Collectors.toList());
    }

    /*
     * 답글 상세보기
     */
    public List<CommentDto> getCommentById(Long qnaId) {
        List<Comment> comments = commentRepository.findByQnaId(qnaId);

        log.info("getCommentById : {}", comments);

        return comments.stream().map(comment -> new CommentDto(
                comment.getQnaId(),
                comment.getContent(),
                comment.getImageFileName()
        )).collect(Collectors.toList());
    }

//    /*
//     * QnA 답글 작성 (사용자)
//     * String identifier
//     */
//    @Transactional
//    public Comment createCommentByUser(String username, String originalFilename, CommentDto commentDto) {
//        User user = userRepository.findByUsername(username).orElseThrow(RuntimeException::new);
//        commentDto.setImageFileName(originalFilename);
//        Comment comment = Comment.createUserComment(commentDto);
//        comment.addCommentByUser(user);
//        return commentRepository.save(comment);
//    }
//
//    /*
//     * QnA 답글 작성 (관리자)
//     * String identifier
//     */
//    @Transactional
//    public Comment createCommentByAdmin(String username, String originalFilename, CommentDto commentDto) {
//        Admin admin = adminRepository.findByUsername(username).orElseThrow(RuntimeException::new);
//        commentDto.setImageFileName(originalFilename);
//        Comment comment = Comment.createAdminComment(commentDto);
//        comment.addCommentByAdmin(admin);
//        return commentRepository.save(comment);
//    }

    /**
     * 댓글 리스트 조회
     */
//    public List<Comment> findAllCommentsByQnaId(Long qnaId) {
//        return commentRepository.findAllByQnaId(qnaId);
//    }
}

package com.like.pro5.controller;

import com.like.pro5.controller.dto.CommentDto;
import com.like.pro5.controller.dto.UserDto;
import com.like.pro5.domain.entity.post.Comment;
import com.like.pro5.domain.repository.CommentRepository;
import com.like.pro5.security.custom.CustomUserDetails;
import com.like.pro5.service.CommentService;
import com.like.pro5.service.FileService;
import com.like.pro5.service.QnaService;
import com.like.pro5.util.trace.TraceStatus;
import com.like.pro5.util.trace.log.ThreadLocalLogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CommentRepository commentRepository;

    @GetMapping("/comments")
    public String findAllComment(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        List<CommentDto> comments = commentService.findAllComment(userDetails);
        model.addAttribute("comments", comments);
        return "qna/list";
    }

    /*
    * Q&A 답변 작성
    * (Admin)
    */
//    @PostMapping("/comments/create")
//    public ResponseEntity<?> createCommentByAdmin(@AuthenticationPrincipal CustomUserDetails userDetails,
//                                                  @RequestParam MultipartFile multipartFile,
//                                                  CommentDto commentDto) throws URISyntaxException {
//
//        log.info("comment content : {}, comment imageFileName : {}", commentDto.getContent(), commentDto.getImageFileName());
//
//        commentService.createCommentByAdmin(userDetails.getUsername(), multipartFile.getOriginalFilename(), commentDto);
//        fileService.imageFileUpload(multipartFile);
//
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }

}

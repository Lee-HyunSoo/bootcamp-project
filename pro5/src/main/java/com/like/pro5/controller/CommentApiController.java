package com.like.pro5.controller;

import com.like.pro5.controller.dto.CommentDto;
import com.like.pro5.controller.dto.UserDto;
import com.like.pro5.domain.repository.CommentRepository;
import com.like.pro5.security.custom.CustomUserDetails;
import com.like.pro5.service.CommentService;
import com.like.pro5.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;
    private final CommentRepository commentRepository;
    private final FileService fileService;

    /*
     * Q&A 답변 작성
     * (Admin)
     */
    @PostMapping("/comments/create")
    public ResponseEntity<?> createCommentByAdmin(@AuthenticationPrincipal CustomUserDetails userDetails,
                                                  @RequestParam MultipartFile multipartFile,
                                                  CommentDto commentDto) {

        log.info("commentDtd : {}", commentDto);

        commentService.createComment(userDetails, multipartFile.getOriginalFilename(), commentDto);
        fileService.imageFileUpload(multipartFile);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

//    // 댓글 리스트 조회
//    @GetMapping("/comments/{qnaId}")
//    public List<CommentDto> findAllComment(@PathVariable Long qnaId) {
//        return commentService.findAllComment(qnaId);
//    }


//    /*
//     * Q&A 답변 작성
//     * (user)
//     */
//    @PostMapping("/comments/create")
//    public ResponseEntity<?> createCommentByUser(@AuthenticationPrincipal CustomUserDetails userDetails,
//                                                  @RequestParam MultipartFile multipartFile,
//                                                  CommentDto commentDto) throws URISyntaxException {
//
//        log.info("comment content : {}, comment imageFileName : {}", commentDto.getContent(), commentDto.getImageFileName());
//
//        commentService.createCommentByUser(userDetails.getUsername(), multipartFile.getOriginalFilename(), commentDto);
//        fileService.imageFileUpload(multipartFile);
//
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }


    /*
    * Admin이 작성한 답변 불러옴
    */
//    @GetMapping("/comments/list")
//    public ResponseEntity<?> commentList(){
//
//
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
//
//    @GetMapping("/list")
//    public String listComment(Model model) {
//        List<CommentDto> commentList = this.commentService.getAllQnaList();
//        model.addAttribute("commentList", commentList);
//
//        return "qna/list";
//    }
}

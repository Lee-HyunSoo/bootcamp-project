package com.like.pro5.controller;

import com.like.pro5.controller.dto.CommentDto;
import com.like.pro5.controller.dto.QnaDto;
import com.like.pro5.service.CommentService;
import com.like.pro5.service.FileService;
import com.like.pro5.service.QnaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Controller
@RequestMapping("/qna")
@RequiredArgsConstructor
public class PostController {

    private final QnaService qnaService;
    private final CommentService commentService;
    private final FileService fileService;

    // QnA 목록 조회 페이지
    @GetMapping("/list")
    public String listQna(Model model) {
        List<QnaDto> qnaDtoList = this.qnaService.getAllQnaList();
        model.addAttribute("qnaList", qnaDtoList);

        return "qna/list";
    }

    // QnA 질문 검색
    @PostMapping("/list")
    public String searchQnaKeyword(@RequestParam(defaultValue = "") String search,
                                   @RequestParam(defaultValue = "") String keyword,
                                   Model model, Pageable pageable) {
        log.info("search : {}, keyword : {}", search, keyword);

        Page<QnaDto> qnaList = qnaService.searchByKeyword(search, keyword, pageable);
        model.addAttribute("qnaList", qnaList);

        return "/qna/list";
    }

    // QnA form으로 이동
    @GetMapping("/create")
    public String createQnaForm() {
        return "qna/create";
    }

    // 제목을 기준으로 상세보기
    @GetMapping("/detail/{qnaId}")
    public String detailQnaByTitle(@PathVariable Long qnaId, Model model) {
        log.info("qna title : {}", qnaId);
        QnaDto qnaDto = qnaService.getQnaById(qnaId);
        List<CommentDto> commentDtos = commentService.getCommentById(qnaId);

        log.info("commentDtos im detailQnaByTitle : {}", commentDtos);

        model.addAttribute("qnaDto", qnaDto);
        model.addAttribute("commentDtos", commentDtos);

        return "qna/detail";
    }

    // 질문 내역 수정 (기존 값을 수정 페이지로)
    @GetMapping("/edit/{qnaId}")
    public String update(@PathVariable Long qnaId, Model model) {
        try {
            QnaDto qnaDto = this.qnaService.getQnaById(qnaId);
            model.addAttribute("qnaDto", qnaDto);
            return "qna/edit";

        } catch (NoSuchElementException e) {
            return "qna/list";
        }
    }

    // 질문 내역 수정 (submit)
//    @PostMapping("/edit/{qnaId}")
//    public String update(@PathVariable Long qnaId,
//                         @RequestParam String title,
//                         @RequestParam String content) {
//        try {
//            qnaService.modifyQnaByUser(qnaId, title, content);
//            return "redirect:/qna/detail/" + title;
//
//        } catch (NoSuchElementException e) {
//            return "redirect:/qna/list";
//        }
//    }

    // 질문 내역 삭제
    @GetMapping("/delete/{qnaId}")
    public String deleteQna(@PathVariable Long qnaId) {
        qnaService.deleteQna(qnaId);
        return "redirect:/qna/list";
    }

}

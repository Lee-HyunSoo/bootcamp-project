package com.like.pro5.controller;

import com.like.pro5.controller.dto.QnaDto;
import com.like.pro5.controller.dto.UserDto;
import com.like.pro5.security.custom.CustomUserDetails;
import com.like.pro5.service.FileService;
import com.like.pro5.service.QnaService;
import com.like.pro5.util.trace.TraceStatus;
import com.like.pro5.util.trace.log.ThreadLocalLogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

/**
 * Q&A 기능을 제공하는 Spring MVC 컨트롤러
 * 클라이언트의 요청을 처리하고 관련 기능을 수행
 */
@Slf4j
@RestController
@RequestMapping("/qna")
@RequiredArgsConstructor
public class PostApiController {

    private final QnaService qnaService;
    private final FileService fileService;
    private final ThreadLocalLogTrace trace;


    // Q&A 질문 작성 (submit)
    @PostMapping("/create")
    public ResponseEntity<?> createQnaByUser(@AuthenticationPrincipal CustomUserDetails userDetails,
                                             @RequestParam MultipartFile multipartFile,
                                             QnaDto qnaDto) {

        TraceStatus status = trace.begin("FileController.imageFileUpload()");
        log.info("userDetails : {}", userDetails.getUsername());
        log.info("qna title : {}, qna content : {}, qna imageFileName : {}", qnaDto.getTitle(), qnaDto.getContent(), qnaDto.getImageFileName());

        qnaService.createQnaByUser(userDetails.getUsername(), multipartFile.getOriginalFilename(), qnaDto);
        fileService.imageFileUpload(multipartFile);
//        qnaService.createQnaByUser(multipartFile.getOriginalFilename(), qnaDto);
        trace.end(status);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 질문 내역 수정 (submit)
    @PostMapping("/edit")
    public ResponseEntity<?> update(@RequestParam MultipartFile multipartFile,
                                    QnaDto qnaDto) throws MalformedURLException {

        // 파일이 비어있으면 새로 파일 업로드
        if(multipartFile.isEmpty()){
            qnaService.modifyQnaByUser(qnaDto.getQnaId(), qnaDto.getTitle(), qnaDto.getContent(), multipartFile.getOriginalFilename());
            fileService.imageFileUpload(multipartFile);
            return ResponseEntity.status(HttpStatus.OK).build();

        } else {    // 그외의 상황은 파일을 업로드한 상황, 파일 명과 경로를 가져와서 수정함
            Resource resource = fileService.getImageResource(multipartFile.getOriginalFilename());
            multipartFile.getResource();
            qnaService.modifyQnaByUser(qnaDto.getQnaId(), qnaDto.getTitle(), qnaDto.getContent(), resource.getFilename());
            fileService.imageFileUpload(multipartFile);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }

    //        Resource resource = fileService.getImageResource(multipartFile.getOriginalFilename());
//        if (resource.exists() && resource.isReadable()) {
//            qnaService.modifyQnaByUser(qnaDto.getTitle(), qnaDto.getContent(), multipartFile.getOriginalFilename());
//            fileService.imageFileUpload(multipartFile);
//            return "redirect:/qna/list";
//        } else {
//            qnaService.modifyQnaByUser(qnaId, title, content, multipartFile.getOriginalFilename());
//            return "redirect:/qna/detail/" + title;
//
//        }



//    @PostMapping("/edit")
//    public ResponseEntity<?> update(@RequestParam MultipartFile multipartFile,
//                                    QnaDto qnaDto) throws MalformedURLException {
//
//        qnaService.modifyQnaByUser(qnaDto.getTitle(), qnaDto.getContent(), multipartFile.getOriginalFilename());
//        fileService.imageFileUpload(multipartFile);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }

}

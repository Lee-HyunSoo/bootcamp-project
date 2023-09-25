package com.like.pro5.controller;

import com.like.pro5.service.FileService;
import com.like.pro5.util.trace.TraceStatus;
import com.like.pro5.util.trace.log.ThreadLocalLogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@AuthenticationPrincipal UserDto userDto
@RequiredArgsConstructor
@Controller
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;
    private final ThreadLocalLogTrace trace;

    @GetMapping("/upload")
    public String file() {
        return "/file/upload";
    }

    @ResponseBody
    @PostMapping("/upload")
    public String imageFileUpload(@RequestParam MultipartFile multipartFile) {
        TraceStatus status = trace.begin("FileController.imageFileUpload()");
        fileService.imageFileUpload(multipartFile);
        trace.end(status);

        return "업로드 성공 - 파일 이름 : " + multipartFile.getOriginalFilename();
    }

    // 업로드 페이지
    @GetMapping("/tus")
    public String tusUploadPage() {
        return "file/tus";
    }

    // 업로드 엔드포인트
    @ResponseBody
    @RequestMapping(value = {"/tus/upload", "/tus/upload/**"})
    public ResponseEntity<String> tusUpload(HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.ok(fileService.process(request, response));
    }

}

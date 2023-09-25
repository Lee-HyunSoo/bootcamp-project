package com.like.pro5.controller;

import com.like.pro5.controller.dto.CommentDto;
import com.like.pro5.controller.dto.ItemDto;
import com.like.pro5.controller.dto.QnaDto;
import com.like.pro5.controller.dto.UserDto;
import com.like.pro5.security.custom.CustomUserDetails;
import com.like.pro5.service.AdminService;
import com.like.pro5.service.AuthService;
import com.like.pro5.util.trace.TraceStatus;
import com.like.pro5.util.trace.log.ThreadLocalLogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final ThreadLocalLogTrace trace;

    @GetMapping("/users")
    public String findAllUsers(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "") String keyword,
            @PageableDefault Pageable pageable, Model model) {
        TraceStatus status = trace.begin("AdminController.findAllUsers()");
        Page<UserDto> users = adminService.findAllUsers(search, keyword, pageable);
        model.addAttribute("users", users);
        trace.end(status);
        return "admin/users";
    }


    @GetMapping("/items")
    public String findAllItems(
            @RequestParam(defaultValue = "NONE") String category,
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "") String keyword,
            @PageableDefault Pageable pageable, Model model) {
        TraceStatus status = trace.begin("AdminController.findAllItems()");
        Page<ItemDto> items = adminService.findAllItems(category, search, keyword, pageable);
        model.addAttribute("items", items);
        trace.end(status);
        return "admin/items";
    }

    @GetMapping("/items/lecture")
    public String createLecture() {
        return "admin/create";
    }

    @PostMapping("/items/lecture")
    public String createNewLecture(@ModelAttribute ItemDto itemDto, @RequestParam("filename") MultipartFile file) {

        TraceStatus status = trace.begin("AdminController.createNewLecture()");
        String fileContent = file.getOriginalFilename();
        itemDto.setVideoFileName(fileContent);
        itemDto.setThumbnailFileName(fileContent);
        adminService.createNewLecture(itemDto);
        trace.end(status);
        return "redirect:/admin/items";
    }

    @GetMapping("/qnas")
    public String findAllQnas(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "") String keyword,
            @PageableDefault Pageable pageable, Model model) {
        TraceStatus status = trace.begin("AdminController.findAllQnas()");
        Page<QnaDto> qnas = adminService.findAllQnas(search, keyword, pageable);
        model.addAttribute("qnas", qnas);
        trace.end(status);
        return "admin/qnas";
    }

    @PostMapping("/comments")
    public String findAllComment(@AuthenticationPrincipal CustomUserDetails userDetails,
                                 MultipartFile multipartFile,
                                 CommentDto commentDto) {
        TraceStatus status = trace.begin("AdminController.findAllComment()");
        adminService.createComment(userDetails, multipartFile.getOriginalFilename(), commentDto);
        trace.end(status);
        return "redirect:/admin/items";
    }

}

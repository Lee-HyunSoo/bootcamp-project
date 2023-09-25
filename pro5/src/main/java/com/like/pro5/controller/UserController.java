package com.like.pro5.controller;

import com.like.pro5.controller.dto.ItemDto;
import com.like.pro5.controller.dto.querydsl.UserLectureDto;
import com.like.pro5.security.custom.CustomUserDetails;
import com.like.pro5.service.ItemService;
import com.like.pro5.service.UserService;
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

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ItemService itemService;
    private final ThreadLocalLogTrace trace;

    @GetMapping("/introduction")
    public String introduction() {
        return "introduction";
    }

    @GetMapping("/course")
    public String course(@AuthenticationPrincipal CustomUserDetails userDetails,
                         @PageableDefault(size = 5) Pageable pageable, Model model) {

        Page<UserLectureDto> lectures = userService.searchByKeyword(userDetails.getUsername(), "", "", pageable);
        model.addAttribute("lectures", lectures);

        return "users/course";
    }


    @PostMapping("/course")
    public String course(@AuthenticationPrincipal CustomUserDetails userDetails,
                         @RequestParam(defaultValue = "") String search,
                         @RequestParam(defaultValue = "") String keyword,
                         @PageableDefault(size = 5) Pageable pageable,
                         Model model) {

        Page<UserLectureDto> lectures = userService.searchByKeyword(userDetails.getUsername(), search, keyword, pageable);
        model.addAttribute("lectures", lectures);

        return "users/course";
    }

    @GetMapping(value="/detail/{id}")
    public String itemDetail(@PathVariable Long id,
                             Model model) {
        ItemDto itemDto = itemService.findByLecture(id);

        log.info("itemDetail : {}", itemDto);
        model.addAttribute("itemDetail", itemDto);

        return "users/courseDetail";
    }

    @GetMapping("/settings")
    public String settings() {
        return "users/settings";
    }

    @PostMapping("/settings")
    public String changedPassword(@AuthenticationPrincipal CustomUserDetails userDetails,
                                  @RequestParam String prePassword,
                                  @RequestParam String nextPassword) {

        userService.changedPassword(userDetails.getUsername(), prePassword, nextPassword);
        return "redirect:/auth/logout";
    }

    @GetMapping("/settings/delete")
    public String deleteUser() {
        return "users/settings/delete";
    }

    @PostMapping("/settings/delete")
    public String deleteUser(@AuthenticationPrincipal CustomUserDetails userDetails) {

        userService.deleteUser(userDetails.getUsername());
        return "redirect:/auth/logout";
    }
}

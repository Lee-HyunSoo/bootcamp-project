package com.like.pro5.controller;

import com.like.pro5.controller.dto.UserDto;
import com.like.pro5.domain.entity.Role;
import com.like.pro5.domain.entity.User;
import com.like.pro5.domain.entity.item.Category;
import com.like.pro5.service.AuthService;
import com.like.pro5.util.trace.TraceStatus;
import com.like.pro5.util.trace.log.ThreadLocalLogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final ThreadLocalLogTrace trace;

    @GetMapping("/join")
    public String signUp() {
        return "join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute UserDto userDto) {
        TraceStatus status = trace.begin("AuthController.join()");
        authService.join(userDto);
        trace.end(status);
        return "redirect:/auth/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserDto userDto) {
        TraceStatus status = trace.begin("AuthController.login()");
        authService.login(userDto);

        trace.end(status);
        return "redirect:/";
    }

    @GetMapping("/admin/login")
    public String adminLogin() {
        return "admin/login";
    }

    @PostMapping("/admin/login")
    public String adminLogin(@ModelAttribute UserDto userDto) {
        TraceStatus status = trace.begin("AdminController.login()");
        authService.login(userDto);
        trace.end(status);
        return "redirect:/admin/users";
    }

    @PostConstruct
    @Transactional
    public void init() {
        UserDto userDto = new UserDto();
        userDto.setUsername("test");
        userDto.setPassword("test");
        userDto.setName("test");
        userDto.setTel("010-1234-1234");
        userDto.setEmail("test@naver.com");
        userDto.setCity("경기");
        userDto.setStreet("고양");
        userDto.setZipcode("행신");
        authService.join(userDto);
    }


}

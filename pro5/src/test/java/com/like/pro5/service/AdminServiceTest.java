package com.like.pro5.service;

import com.like.pro5.controller.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AdminServiceTest {

    @Autowired
    AdminService adminService;
    @Autowired
    UserService userService;
    @Autowired
    AuthService authService;



    @Test
    void findAllUsers() {
        // given
        UserDto userDto1 = new UserDto();
        userDto1.setUsername("test1");
        userDto1.setPassword("test1");
        authService.join(userDto1);


        UserDto userDto2 = new UserDto();
        userDto2.setUsername("test2");
        userDto2.setPassword("test2");
        authService.join(userDto2);

        UserDto userDto3 = new UserDto();
        userDto3.setUsername("test3");
        userDto3.setPassword("test3");
        authService.join(userDto3);

        // when
        PageRequest pageRequest = PageRequest.of(0, 5);
//        Page<UserDto> users = adminService.findAllUsers(pageRequest);

        // then
//        for (UserDto user : users) {
//            System.out.println("user = " + user);
//        }

    }

}
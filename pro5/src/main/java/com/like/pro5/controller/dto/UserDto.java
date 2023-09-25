package com.like.pro5.controller.dto;

import com.like.pro5.domain.entity.Role;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto  {

    @NotBlank(message = "아이디를 입력해주세요.")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password = "";

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Size(min = 2, max = 16)
    private String name;

    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @NotBlank(message = "전화번호를 입력해주세요.")
    private String tel;

    private String city;
    private String street;
    private String zipcode;

    private Role role;

    @QueryProjection
    public UserDto(String username, String password, String name, String email, String tel, String city, String street, String zipcode) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }


    //    private List<OrderDto> orders;
//    private List<ItemDto> items;
//    private List<QnaDto> posts;

}
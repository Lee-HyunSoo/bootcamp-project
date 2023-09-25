package com.like.pro5.domain.entity;

import com.like.pro5.controller.dto.UserDto;
import com.like.pro5.domain.entity.auditing.BaseTimeEntity;
import com.like.pro5.domain.entity.item.Item;
import com.like.pro5.domain.entity.post.Post;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String username; // 아이디
    private String password; // 비번
    private String name; // 닉네임
    private String email; // 이메일
    private String tel; // 전화번호

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private Role role; // [USER, ADMIN]

    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Item> items = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();


    // == 생성 메서드 ==
    public static User createUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setTel(userDto.getTel());
        user.setAddress(new Address(
                userDto.getCity(),
                userDto.getStreet(),
                userDto.getZipcode()
        ));
        user.setRole(Role.USER);
        return user;
    }

}

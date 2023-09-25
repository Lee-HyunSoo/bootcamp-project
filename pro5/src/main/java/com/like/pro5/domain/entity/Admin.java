package com.like.pro5.domain.entity;

import com.like.pro5.domain.entity.item.Item;
import com.like.pro5.domain.entity.post.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Admin {

    @Id
    @GeneratedValue
    @Column(name = "admin_id")
    private Long id;

    private String username; // 아이디
    private String password; // 비번
    private String name; // 닉네임

    @Enumerated(EnumType.STRING)
    private Role role; // [USER, ADMIN]

    @OneToMany(mappedBy = "admin")
    private List<Item> items = new ArrayList<>();

    @OneToMany(mappedBy = "admin")
    private List<Post> posts = new ArrayList<>();

    public static Admin createAdmin() {
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setName("관리자");
        admin.setRole(Role.ADMIN);
        return admin;
    }

}

package com.like.pro5.domain.entity.item;

import com.like.pro5.domain.entity.Admin;
import com.like.pro5.domain.entity.User;
import com.like.pro5.domain.entity.auditing.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
@Entity
public abstract class Item extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String title;
    private String content;
    private String videoFileName;
    private String thumbnailFileName;
    private int price;
    private int quantity;

    @Enumerated(EnumType.STRING)
    private Category category; // [JAVA, C, PYTHON]

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;


}
package com.like.pro5.domain.entity;

import com.like.pro5.domain.repository.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static com.like.pro5.domain.entity.QUser.*;

@SpringBootTest
@Transactional
class UserTest {
    
    @Autowired
    UserRepository userRepository;
    @PersistenceContext
    EntityManager em;
    JPAQueryFactory queryFactory;

    @BeforeEach
    void before() {
        queryFactory = new JPAQueryFactory(em);


    }
    
    @Test
    public void basic() throws Exception {
        // given
        User user1 = new User();
        user1.setName("김");
        User user2 = new User();
        user1.setName("이");
        User user3 = new User();
        user1.setName("박");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        // when
        List<User> users = queryFactory
                .selectFrom(user)
                .fetch();

        // then
        for (User u : users) {
            System.out.println("users = " + u);
        }

    
    }
    

}
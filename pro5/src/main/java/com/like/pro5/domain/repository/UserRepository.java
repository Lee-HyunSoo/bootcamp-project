package com.like.pro5.domain.repository;

import com.like.pro5.domain.entity.User;
import com.like.pro5.domain.repository.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    Optional<User> findByUsername(String username);
    Optional<User> findByNameAndPassword(String name, String password); // 아이디 찾기
    void deleteByUsername(String username); // 회원 탈퇴
    boolean existsByUsername(String username);

    @Query("select distinct u from User u left join fetch u.items where u.username = :username")
    Optional<User> findItemsFetch(@Param("username") String username);

}

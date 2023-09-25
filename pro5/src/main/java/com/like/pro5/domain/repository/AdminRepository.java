package com.like.pro5.domain.repository;

import com.like.pro5.domain.entity.Admin;
import com.like.pro5.domain.repository.custom.AdminRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long>, AdminRepositoryCustom {

    Optional<Admin> findByUsername(String username);
}

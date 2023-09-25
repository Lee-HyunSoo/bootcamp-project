package com.like.pro5.service;

import com.like.pro5.controller.dto.UserDto;
import com.like.pro5.controller.dto.querydsl.ItemSearchCondition;
import com.like.pro5.controller.dto.querydsl.UserLectureDto;
import com.like.pro5.domain.entity.Admin;
import com.like.pro5.domain.entity.User;
import com.like.pro5.domain.repository.UserRepository;
import com.like.pro5.util.trace.TraceStatus;
import com.like.pro5.util.trace.log.ThreadLocalLogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ThreadLocalLogTrace trace;

    /**
     * 비밀번호 변경
     */
    @Transactional
    public void changedPassword(String username, String prePw, String nextPw) {
        TraceStatus status = trace.begin("UserService.changedPassword()");

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));

        /* 입력한 이전 비밀번호, 현재 DB 에 저장 되어있는 비밀번호 */
        if (passwordEncoder.matches(prePw, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(nextPw));
        }
        else {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }

        trace.end(status);
    }

    /**
     * 회원탈퇴
     */
    @Transactional
    public void deleteUser(String username) {
        TraceStatus status = trace.begin("UserService.deleteUser()");
        userRepository.deleteByUsername(username);

        trace.end(status);
    }

    /**
     * 구매한 강좌들 불러오기, 검색 기능 [제목, 내용]
     * 페이징
     * 정렬 [출시순, 가나다순, 진행중인순서]
     */
    public Page<UserLectureDto> searchByKeyword(String username,
                                                String search,
                                                String keyword,
                                                Pageable pageable) {
        TraceStatus status = trace.begin("UserService.searchByKeyword()");

        ItemSearchCondition itemSearchCondition = setItemSearchCondition(username, search, keyword);

        trace.end(status);
        return userRepository.searchByKeyword(itemSearchCondition, pageable);
    }

    // == 내부 메서드 ==
    private ItemSearchCondition setItemSearchCondition(String username, String title, String content) {
        TraceStatus status = trace.begin("UserService.setItemSearchCondition()");

        ItemSearchCondition itemSearchCondition = new ItemSearchCondition();
        itemSearchCondition.setUsername(username);
        itemSearchCondition.setTitle(title);
        itemSearchCondition.setContent(content);

        trace.end(status);
        return itemSearchCondition;
    }
}

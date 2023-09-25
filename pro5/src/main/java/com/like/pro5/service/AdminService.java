package com.like.pro5.service;

import com.like.pro5.controller.dto.CommentDto;
import com.like.pro5.controller.dto.ItemDto;
import com.like.pro5.controller.dto.QnaDto;
import com.like.pro5.controller.dto.UserDto;
import com.like.pro5.controller.dto.querydsl.ItemSearchCondition;
import com.like.pro5.controller.dto.querydsl.QnaSearchCondition;
import com.like.pro5.controller.dto.querydsl.UserSearchCondition;
import com.like.pro5.domain.entity.Admin;
import com.like.pro5.domain.entity.Role;
import com.like.pro5.domain.entity.User;
import com.like.pro5.domain.entity.item.Book;
import com.like.pro5.domain.entity.item.Category;
import com.like.pro5.domain.entity.item.Item;
import com.like.pro5.domain.entity.item.Lecture;
import com.like.pro5.domain.entity.post.Comment;
import com.like.pro5.domain.repository.*;
import com.like.pro5.security.custom.CustomUserDetails;
import com.like.pro5.util.trace.TraceStatus;
import com.like.pro5.util.trace.log.ThreadLocalLogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AdminService {

    private final AdminRepository adminRepository;
    private final CommentRepository commentRepository;
    private final ItemRepository itemRepository;
    private final ThreadLocalLogTrace trace;

    @PostConstruct
    @Transactional
    public void init() {
        Admin admin = Admin.createAdmin();
        adminRepository.save(admin);
    }

    /**
     * 유저 리스트
     */
    public Page<UserDto> findAllUsers(String search, String keyword, Pageable pageable) {
        TraceStatus status = trace.begin("AdminService.findAllUser()");
        UserSearchCondition userSearchCondition = setUserSearchCondition(search, keyword);
        trace.end(status);
        return adminRepository.findAllUsers(userSearchCondition, pageable);
    }

    /**
     * 강의 리스트
     */
    public Page<ItemDto> findAllItems(String category, String search, String keyword, Pageable pageable) {
        TraceStatus status = trace.begin("AdminService.findAllItem()");
        ItemSearchCondition itemSearchCondition = setItemSearchCondition(Category.valueOf(category), search, keyword);
        trace.end(status);
        return adminRepository.findAllItems(itemSearchCondition, pageable);
    }

    /**
     * 강의 업로드
     */
    @Transactional
    public void createNewLecture(ItemDto itemDto) {
        TraceStatus status = trace.begin("AdminService.createLecture()");
        Lecture lecture = Lecture.createNewLecture(itemDto);
        itemRepository.save(lecture);
        trace.end(status);
    }

    /**
     * 질문 리스트
     */
    public Page<QnaDto> findAllQnas(String search, String keyword, Pageable pageable) {
        TraceStatus status = trace.begin("AdminService.findAllQna()");
        QnaSearchCondition qnaSearchCondition = setQnaSearchCondition(search, keyword);
        trace.end(status);
        return adminRepository.findAllQnas(qnaSearchCondition, pageable);
    }

    /**
     * 코멘트 달기
     */
    @Transactional
    public void createComment(CustomUserDetails userDetails, String originalFilename, CommentDto commentDto) {
        commentDto.setImageFileName(originalFilename);
        Comment comment = Comment.createComment(commentDto);
        Admin admin = adminRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(RuntimeException::new);
        comment.addCommentByAdmin(admin);
        commentRepository.save(comment);
    }

    private UserSearchCondition setUserSearchCondition(String search, String keyword) {
        TraceStatus status = trace.begin("AdminService.setItemSearchCondition()");
        UserSearchCondition userSearchCondition = new UserSearchCondition();

        /* 검색어를 통한 title, content 설정 */
        if (search.equals("username")) {
            userSearchCondition.setUsername(keyword);
        } else if (search.equals("name")) {
            userSearchCondition.setName(keyword);
        } else if (search.equals("email")){
            userSearchCondition.setEmail(keyword);
        } else {
            userSearchCondition.setUsername(keyword);
            userSearchCondition.setName(keyword);
            userSearchCondition.setEmail(keyword);
        }

        trace.end(status);
        return userSearchCondition;
    }

    private ItemSearchCondition setItemSearchCondition(Category category, String search, String keyword) {
        TraceStatus status = trace.begin("AdminService.setItemSearchCondition()");
        ItemSearchCondition itemSearchCondition = new ItemSearchCondition();
        /* category select 를 통한 category 설정 */
        itemSearchCondition.setCategory(category);

        /* 검색어를 통한 title, content 설정 */
        if (search.equals("title")) {
            itemSearchCondition.setTitle(keyword);
        } else if (search.equals("content")) {
            itemSearchCondition.setContent(keyword);
        } else if (search.equals("titleContent")){
            itemSearchCondition.setTitle(keyword);
            itemSearchCondition.setContent(keyword);
        }

        trace.end(status);
        return itemSearchCondition;
    }

    private QnaSearchCondition setQnaSearchCondition(String search, String keyword) {
        TraceStatus status = trace.begin("AdminService.setQnaSearchCondition()");
        QnaSearchCondition qnaSearchCondition = new QnaSearchCondition();

        /* 검색어를 통한 title, content 설정 */
        if (search.equals("title")) {
            qnaSearchCondition.setTitle(keyword);
        } else if (search.equals("content")) {
            qnaSearchCondition.setContent(keyword);
        }

        trace.end(status);
        return qnaSearchCondition;
    }

    @RequiredArgsConstructor
    @Component
    class InitOrder {

        private final ItemRepository itemRepository;
        private final ItemService itemService;
        private final OrderService orderService;

        @PostConstruct
        @Transactional
        public void init() {
            Item item1 = itemRepository.findById(5L).orElseThrow(RuntimeException::new);
            Item item2 = itemRepository.findById(8L).orElseThrow(RuntimeException::new);
            ItemDto itemDto1 = new ItemDto();
            itemDto1.setCategory(item1.getCategory());
            itemDto1.setContent(item1.getContent());
            itemDto1.setPrice(item1.getPrice());
            itemDto1.setQuantity(item1.getQuantity());
            itemDto1.setVideoFileName(item1.getVideoFileName());
            itemDto1.setThumbnailFileName(item1.getThumbnailFileName());
            itemDto1.setTitle(item1.getTitle());

            ItemDto itemDto2 = new ItemDto();
            itemDto2.setCategory(item2.getCategory());
            itemDto2.setContent(item2.getContent());
            itemDto2.setPrice(item2.getPrice());
            itemDto2.setQuantity(item2.getQuantity());
            itemDto2.setVideoFileName(item2.getVideoFileName());
            itemDto2.setThumbnailFileName(item2.getThumbnailFileName());
            itemDto2.setTitle(item2.getTitle());

            itemService.addCart("test", itemDto1);
            itemService.addCart("test", itemDto2);
            orderService.createOrder("test");
        }
    }


}

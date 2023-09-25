package com.like.pro5.service;

import com.like.pro5.controller.dto.ItemDto;
import com.like.pro5.controller.dto.querydsl.ItemSearchCondition;
import com.like.pro5.domain.entity.User;
import com.like.pro5.domain.entity.item.Book;
import com.like.pro5.domain.entity.item.Category;
import com.like.pro5.domain.entity.item.Item;
import com.like.pro5.domain.entity.item.Lecture;
import com.like.pro5.domain.repository.ItemRepository;
import com.like.pro5.domain.repository.UserRepository;
import com.like.pro5.util.trace.TraceStatus;
import com.like.pro5.util.trace.log.ThreadLocalLogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final ThreadLocalLogTrace trace;

    @PostConstruct
    @Transactional
    public void init() {
        Lecture coffeeLhs = Lecture.addLecture("인생의 쓴맛", 1800, 1,"모닝커피는 인생의 진리지",
                Category.JAVA, "090310.png", "090310.mp4");
        itemRepository.save(coffeeLhs);


        Lecture footLhs = Lecture.addLecture("우유빛깔 lhs", 10000, 1,"역시 발까지 완벽하게 우유빛을 띄는 lhs..", Category.C,
                "3388.png", "3388.mp4");
        itemRepository.save(footLhs);


        Lecture smileLhs = Lecture.addLecture("치명적 미소의 lhs", 150000, 1,"아침부터 사람 여럿 실려나가게하는 미소의 lhs..", Category.PYTHON,
                "20230924.png", "20230924.mp4");
        itemRepository.save(smileLhs);

        Lecture buyLhs = Lecture.addLecture("등까지 빛이나는 lhs", 20000, 1,"태평양같은 그의 어깨에 누워 잠들고 싶어요", Category.JAVA,
                "085201.png", "085201.mp4");
        itemRepository.save(buyLhs);

        Lecture cuteLhs = Lecture.addLecture("미간까지 미남인 lhs", 300000, 1,"미간 사이에 텐트치고 자고싶어요", Category.JAVA,
                "20230925.png", "20230925.mp4");
        itemRepository.save(cuteLhs);

        Lecture sooLhs = Lecture.addLecture("[아이컨택캠] 이현수", 50000000, 1,"단독직캠 한정판매", Category.JAVA,
                "soo2.png", "soo2.mp4");
        itemRepository.save(sooLhs);

        for (int i = 0; i < 3; i++) {
            Lecture lectureDong = Lecture.addLecture("spring" + i, 1, 1,"springBoot" + i,
                    Category.JAVA, "716313268.png", "716313268.309936.mp4");
            itemRepository.save(lectureDong);
        }

        for (int i = 0; i < 3; i++) {
            Lecture lectureDong = Lecture.addLecture("spring" + i, 1, 1,"springBoot" + i, Category.C,
                    "716313268.png", "716313268.309936.mp4");
            itemRepository.save(lectureDong);
        }



        for (int i = 0; i < 3; i++) {
            Lecture lecture = Lecture.addLecture("spring" + i, 1, 1,"springBoot" + i, Category.PYTHON,
                    "716313268.png", "716313268.309936.mp4");
            itemRepository.save(lecture);
        }

        for (int i = 0; i < 3; i++) {
            Book book = Book.addBook("test" + i, "malcha" + i, "spring" + i, "springBoot" + i, Category.JAVA,
                    "java.jpg");
            itemRepository.save(book);
        }

        for (int i = 0; i < 3; i++) {
            Book book = Book.addBook("test" + i, "malcha" + i, "spring" + i, "springBoot" + i, Category.PYTHON,
                    "python.jpg");
            itemRepository.save(book);
        }

        for (int i = 0; i < 3; i++) {
            Book book = Book.addBook("test" + i, "malcha" + i, "spring" + i, "springBoot" + i, Category.C,
                    "cc.jpg");
            itemRepository.save(book);
        }
    }
    
    /**
     * 강의 검색
     */
    public Page<ItemDto> searchByKeyword(String category, String search, String keyword, Pageable pageable) {
        TraceStatus status = trace.begin("ItemService.searchByKeyword()");
        ItemSearchCondition itemSearchCondition = setItemSearchCondition(Category.valueOf(category), search, keyword);
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());

        trace.end(status);

        return itemRepository.searchByCondition(itemSearchCondition, pageRequest);
    }

    /**
     * 강의 리스트 페이지 count -> 메인?
     */
    public int lectureTotalPage(String category, String search, String keyword, Pageable pageable) {
        ItemSearchCondition itemSearchCondition = setItemSearchCondition(Category.valueOf(category), search, keyword);
        return itemRepository.lectureTotalPage(itemSearchCondition, pageable);
    }

    /**
     * 강의 상세 페이지
     */
    public ItemDto findByLecture(Long id) {
        Item item = itemRepository.findById(id).orElseThrow();

        log.info("findByLecture Item : {}", item.getVideoFileName());
        ItemDto itemDto = new ItemDto();
        itemDto.setTitle(item.getTitle());
        itemDto.setContent(item.getContent());
        itemDto.setPrice(item.getPrice());
        itemDto.setQuantity(item.getQuantity());
        itemDto.setCategory(item.getCategory());
        itemDto.setVideoFileName(item.getVideoFileName());
        itemDto.setThumbnailFileName(item.getThumbnailFileName());

        return itemDto;
    }

    /**
     * 강의 카테고리에 따라 해당 도서 추천
     */
    public List<ItemDto> findByCategory(String category) {
        Category category1 = Category.valueOf(category);
        List<Book> bookList = itemRepository.findByCategory(category1);
        List<ItemDto> books = new ArrayList<>();
        for (Book book : bookList) {
            ItemDto itemDto = new ItemDto();
            itemDto.setTitle(book.getTitle());
            itemDto.setAuthor(book.getAuthor());
            itemDto.setContent(book.getContent());
            itemDto.setThumbnailFileName(book.getThumbnailFileName());
            itemDto.setCategory(book.getCategory());
            itemDto.setPress(book.getPress());

            books.add(itemDto);
        }

        return books;
    }

    /**
     * 장바구니 추가
     */
    @Transactional
    public void addCart(String username, ItemDto itemDto) {
        TraceStatus status = trace.begin("ItemService.addCart()");
        User user = userRepository.findByUsername(username).orElseThrow(RuntimeException::new);
        Lecture lecture = Lecture.createLecture(user, itemDto);
        Lecture savedLecture = itemRepository.save(lecture);
        log.info("savedLecture.getId : {}", savedLecture.getId());
        trace.end(status);
    }

    /**
     * ItemSearchCondition 세팅
     */
    private ItemSearchCondition setItemSearchCondition(Category category, String search, String keyword) {
        TraceStatus status = trace.begin("ItemService.setItemSearchCondition()");
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





}

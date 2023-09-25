package com.like.pro5.service;

import com.like.pro5.controller.dto.ItemDto;
import com.like.pro5.controller.dto.PaymentDto;
import com.like.pro5.controller.dto.UserDto;
import com.like.pro5.domain.entity.User;
import com.like.pro5.domain.entity.item.Lecture;
import com.like.pro5.domain.repository.ItemRepository;
import com.like.pro5.domain.repository.OrderRepository;
import com.like.pro5.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthService authService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ItemRepository itemRepository;


    @Test
    public void createOrder() throws Exception {
        // given
        UserDto userDto = new UserDto();
        userDto.setUsername("test");
        userDto.setPassword("test");
        User user = User.createUser(userDto);
        userRepository.save(user);

        ItemDto itemDto1 = new ItemDto();
        itemDto1.setTitle("큰애가");
        itemDto1.setContent("잠을 못자요");
        Lecture lecture1 = Lecture.createLecture(user, itemDto1);
        itemRepository.save(lecture1);

        ItemDto itemDto2 = new ItemDto();
        itemDto2.setTitle("큰애는");
        itemDto2.setContent("자책하면 안되요");
        Lecture lecture2 = Lecture.createLecture(user, itemDto2);
        itemRepository.save(lecture2);

        ItemDto itemDto3 = new ItemDto();
        itemDto3.setTitle("큰애는");
        itemDto3.setContent("잘하고 있어요");
        Lecture lecture3 = Lecture.createLecture(user, itemDto3);
        itemRepository.save(lecture3);

        // when
        PaymentDto payment = orderService.createOrder(user.getUsername());

        // then
        assertThat(payment.getUsername()).isEqualTo(user.getUsername());
    }

}
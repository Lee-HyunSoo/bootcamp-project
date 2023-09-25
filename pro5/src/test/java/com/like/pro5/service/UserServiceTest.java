package com.like.pro5.service;

import com.like.pro5.controller.dto.UserDto;
import com.like.pro5.domain.repository.UserRepository;
import com.like.pro5.util.trace.log.LogTrace;
import com.like.pro5.util.trace.log.ThreadLocalLogTrace;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
@Transactional
class UserServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LogTrace logTrace = new ThreadLocalLogTrace();


    @Test
    public void changedPassword() throws Exception {
        // given
        UserDto userDto = new UserDto();
        userDto.setUsername("test");
        userDto.setPassword("test");

        UserDto joinedUser = authService.join(userDto);
        System.out.println("joinedUser.getPassword() = " + joinedUser.getPassword());
        // when
        userService.changedPassword(joinedUser.getUsername(), "test", "1234");

        // then
        System.out.println("joinedUser.getPassword() = " + joinedUser.getPassword());

    }

    @Test
    public void deleteUser() throws Exception {
        // given
        UserDto userDto = new UserDto();
        userDto.setUsername("test");
        userDto.setPassword("test");
        UserDto joinedUser = authService.join(userDto);
        // when
        userService.deleteUser(userDto.getUsername());

        // then

    }

//    @Test
//    public void join() throws Exception {
//        // given
//        UserDto userDto1 = setUserDto("user", "0000", "jj", "test@naver.com", "123-1234-1234");
//        UserDto userDto2 = setUserDto("user", "0000", "jj", "test@naver.com", "123-1234-1234");
//
//        // when
//        // 회원가입 완료 후 return 받은 id 값
//        Long joinId1 = userService.join(userDto1);
//
//        try {
//            // 두번째 회원가입 시도
//            Long joinId = userService.join(userDto2);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//        // return 받은 id 값을 통해 DB를 조회하여 user 를 조회
//        User foundUser = userRepository.findById(joinId1).orElseThrow(RuntimeException::new);
//
//        // then
//        // 회원가입 완료 했을 때의 id 와 조회한 user 의 id 가 같다면 회원가입이 완료됐음을 알 수있다.
//        assertThat(joinId1).isEqualTo(foundUser.getId());
//    }
//
//    @Test
//    public void signIn() throws Exception {
//        // given
//        UserDto userDto = setUserDto("user", "0000", "jj", "test@naver.com", "123-1234-1234");
//        Long joinId = userService.join(userDto);
//
//        // 로그인 창에서 입력 받은 아이디, 비번
//        String identifier = "user";
//        String password = "0000";
//
//        // when
//        UserDto result = userService.signIn(identifier, password);
//
//        // then
//        assertThat(result.getIdentifier()).isEqualTo(userDto.getIdentifier());
//    }
//
//    @Test
//    public void changedPassword() throws Exception {
//        // given
//        UserDto userDto = setUserDto("user", "0000", "jj", "test@naver.com", "123-1234-1234");
//        Long joinId = userService.join(userDto);
//
//        String prePw = "0000";
//        String nextPw = "1234";
//
//        // when
//        userService.changedPassword(prePw, nextPw);
//
//        // then
//
//    }
//
//    @Test
//    public void deleteUser() throws Exception {
//        // given
//        UserDto userDto = setUserDto("user", "0000", "jj", "test@naver.com", "123-1234-1234");
//        Long joinId = userService.join(userDto);
//        User user = userRepository.findById(joinId).orElseThrow(RuntimeException::new);
//
//        // when
//        userService.deleteUser(user.getPassword());
//
//        // then
//    }

//    @Test
//    public void searchByKeyword() throws Exception {
//        TraceStatus trace = logTrace.begin("start");
//        // given
//        UserDto userDto = setUserDto("user", "0000", "jj", "test@naver.com", "123-1234-1234");
//        UserDto joined = userService.join(userDto);
//        User user = userRepository.findByIdentifier(joined.getIdentifier()).orElseThrow(RuntimeException::new);
//
//        for (int i = 0; i < 100; i++) {
//            Lecture lecture = Lecture.createLecture(user, "spring" + i, "springBoot" + i, Category.JAVA);
//            em.persist(lecture);
//        }
//        em.flush();
//        em.clear();
//
//        // when
//        ItemSearchCondition itemSearchCondition = new ItemSearchCondition();
//        itemSearchCondition.setTitle("spr");
//        PageRequest pageRequest = PageRequest.of(1, 5);
//        Page<UserLectureDto> result = userService.searchByKeyword(itemSearchCondition, pageRequest);
//
//        // then
//        for (UserLectureDto userLectureDto : result.getContent()) {
//            System.out.println("userLectureDto = " + userLectureDto);
//        }
//        logTrace.end(trace);
//
//    }
    private UserDto setUserDto(String id, String pw, String username, String mail, String tel) {
        return UserDto.builder()
                .username(id)
                .password(pw)
                .username(username)
                .email(mail)
                .tel(tel)
                .build();
    }


}
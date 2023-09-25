package com.like.pro5.service;

import com.like.pro5.controller.dto.QnaDto;
import com.like.pro5.controller.dto.querydsl.QnaSearchCondition;
import com.like.pro5.domain.entity.User;
import com.like.pro5.domain.entity.post.Qna;
import com.like.pro5.domain.repository.QnaRepository;
import com.like.pro5.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class QnaService {

    private final QnaRepository qnaRepository;
    private final UserRepository userRepository;

    /*
     * QnA 게시글 목록
     */
    public List<QnaDto> getAllQnaList() {
        List<Qna> qnaList = qnaRepository.findAll();

        List<QnaDto> dtoList = qnaList.stream()
                .map(qna -> new QnaDto(qna.getId(), qna.getTitle(), qna.getContent(), qna.getImageFileName()))
                .collect(Collectors.toList());

        return dtoList;
    }

    /*
     * QnA 게시글 작성 (사용자)
     * String identifier
     */
    @Transactional
    public Qna createQnaByUser(String username, String originalFilename, QnaDto qnaDto) {
        User user = userRepository.findByUsername(username).orElseThrow(RuntimeException::new);
        qnaDto.setImageFileName(originalFilename);
        Qna qna = Qna.createUserQna(user, qnaDto);
//        Qna qna = Qna.createUserQna(qnaDto);
        return qnaRepository.save(qna);
    }


    /*
     * QnA 게시글 상세보기
     */
    public QnaDto getQnaById(Long qnaId) {
        Qna qna = this.qnaRepository.findById(qnaId).orElseThrow(RuntimeException::new);
        QnaDto qnaDto = new QnaDto();
        qnaDto.fromQna(qna);

        return qnaDto;
    }

    /*
    * QnA 게시글 수정
    */
    @Transactional
    public void modifyQnaByUser(Long qnaId, String title, String content, String imageFileName) {
        Qna qna = this.qnaRepository.findById(qnaId).orElseThrow(RuntimeException::new);

        qna.setTitle(title);
        qna.setContent(content);
        qna.setImageFileName(imageFileName);
    }


    /*
    * QnA 게시글 삭제
    */
    @Transactional
    public void deleteQna(Long qnaId) {
        Qna qna = qnaRepository.findById(qnaId).orElseThrow(RuntimeException::new);
        qnaRepository.delete(qna);
    }


    /*
     * 제목 / 내용 / 제목 + 내용 검색
     */
    public Page<QnaDto> searchByKeyword(String search, String keyword, Pageable pageable) {
        QnaSearchCondition qnaSearchCondition = setQnaSearchCondition(search, keyword);
        System.out.println("search : " + search + " keyword : " + keyword);
        return qnaRepository.searchByKeyword(qnaSearchCondition, pageable);
    }

    private QnaSearchCondition setQnaSearchCondition(String search, String keyword) {
        QnaSearchCondition qnaSearchCondition = new QnaSearchCondition();

        /* 검색어를 통한 title, content 설정 */
        if (search.equals("title")) {
            qnaSearchCondition.setTitle(keyword);

        } else if (search.equals("content")) {
            qnaSearchCondition.setContent(keyword);

        }
        return qnaSearchCondition;
    }
}


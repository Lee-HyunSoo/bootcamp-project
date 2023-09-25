package com.like.pro5.domain.entity.item;

import com.like.pro5.controller.dto.ItemDto;
import com.like.pro5.domain.entity.User;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue(value = "lecture")
@ToString
@Entity
public class Lecture extends Item {

    // == 생성 메서드 ==
    public static Lecture createLecture(User user, ItemDto itemDto) {
        // 파일 업로드 추가될 시 파라미터 늘어날 예정
        Lecture lecture = new Lecture();
        lecture.setTitle(itemDto.getTitle());
        lecture.setContent(itemDto.getContent());
        lecture.setCategory(itemDto.getCategory());
        lecture.setPrice(itemDto.getPrice());
        lecture.setQuantity(itemDto.getQuantity());
        lecture.setThumbnailFileName(itemDto.getThumbnailFileName());
        lecture.setVideoFileName(itemDto.getVideoFileName());
        lecture.setUser(user);
        return lecture;
    }

    public static Lecture createNewLecture(ItemDto itemDto) {
        // 파일 업로드 추가될 시 파라미터 늘어날 예정
        Lecture lecture = new Lecture();
        lecture.setTitle(itemDto.getTitle());
        lecture.setContent(itemDto.getContent());
        lecture.setCategory(itemDto.getCategory());
        lecture.setQuantity(itemDto.getQuantity());
        lecture.setPrice(itemDto.getPrice());
        lecture.setVideoFileName(itemDto.getVideoFileName());

        String[] split = itemDto.getThumbnailFileName().split("\\.");
        lecture.setThumbnailFileName(split[0] + ".png");
        return lecture;
    }

    // 테스트 용도
    public static Lecture addLecture(String title, int price, int quantity, String content, Category category,
                                     String thumbnailFileName, String videoFileName) {
        // 파일 업로드 추가될 시 파라미터 늘어날 예정
        Lecture lecture = new Lecture();
        lecture.setTitle(title);
        lecture.setPrice(price);
        lecture.setQuantity(quantity);
        lecture.setContent(content);
        lecture.setCategory(category);
        lecture.setThumbnailFileName(thumbnailFileName);
        lecture.setVideoFileName(videoFileName);

        return lecture;
    }

}
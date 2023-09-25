package com.like.pro5.domain.entity.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 추천도서, 판매 안함
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue(value = "book")
@Entity
public class Book extends Item {

    private String author; // 저자
    private String press; // 출판사

    // 삭제 예정
    public static Book addBook(String author, String press, String title, String content, Category category, String thumbnailFileName) {
        // 파일 업로드 추가될 시 파라미터 늘어날 예정
        Book book = new Book();
        book.setAuthor(author);
        book.setPress(press);
        book.setTitle(title);
        book.setContent(content);
        book.setCategory(category);
        book.setThumbnailFileName(thumbnailFileName);

        return book;
    }

}

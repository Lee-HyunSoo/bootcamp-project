package com.like.pro5.domain.repository;

import com.like.pro5.controller.dto.ItemDto;
import com.like.pro5.domain.entity.item.Book;
import com.like.pro5.domain.entity.item.Category;
import com.like.pro5.domain.entity.item.Item;
import com.like.pro5.domain.repository.custom.ItemRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {
    List<Book> findByCategory(Category category);
}

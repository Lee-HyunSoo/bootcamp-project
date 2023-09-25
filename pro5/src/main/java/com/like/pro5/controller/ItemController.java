package com.like.pro5.controller;

import com.like.pro5.controller.dto.ItemDto;
import com.like.pro5.service.ItemService;
import com.like.pro5.util.trace.TraceStatus;
import com.like.pro5.util.trace.log.ThreadLocalLogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;
    private final ThreadLocalLogTrace trace;

    @GetMapping("/list")
    public String searchKeyword(
            @RequestParam(defaultValue = "NONE") String category,
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "") String keyword,
            @PageableDefault(size = 5) Pageable pageable,
            Model model) {

        TraceStatus status = trace.begin("ItemController.searchKeyword()");
        Page<ItemDto> itemLists = itemService.searchByKeyword(category, search, keyword, pageable);
        int totalPage = itemService.lectureTotalPage(category, search, keyword, pageable);

        model.addAttribute("totalPages", itemLists.getTotalPages());
        model.addAttribute("items", itemLists.getContent());

        int startPage = 0;
        int endPage = totalPage-1;

        model.addAttribute("pageable", pageable);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("itemList", itemLists);
        trace.end(status);
        return "item/itemList";
    }

    @GetMapping(value="/detail/{category}/{id}")
    public String itemDetail(
            @PathVariable String category,
            @PathVariable Long id,
            Model model) {
        ItemDto itemDto = itemService.findByLecture(id);
        List<ItemDto> bookList = itemService.findByCategory(category);

        log.info("itemDetail : {}", itemDto);
        model.addAttribute("itemDetail", itemDto);
        model.addAttribute("bookList", bookList);

        return "item/itemDetail";
    }

}

package com.strong.ch02.controller;

import java.util.List;

import com.strong.ch02.model.Book;
import com.strong.ch02.util.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// 返回网页
@Controller
@RequestMapping("/readingList")
public class ReadingListController {
    private static final String reader = "craig";
    private ReadingListRepository readingListRepository;

    @Autowired
    public ReadingListController(ReadingListRepository readingListRepository) {
        this.readingListRepository = readingListRepository;
    }

    // 处理HTTP Get请求
    @RequestMapping(method = RequestMethod.GET)
    public String readersBooks(Model model) {
        List<Book> readingList = readingListRepository.findByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
        }
        return "readingList";
    }

    // 处理HTTP Post请求
    @RequestMapping(method = RequestMethod.POST)
    public String addToReadingList(Book book) {
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/readingList";
    }
}

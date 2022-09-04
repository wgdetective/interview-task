package com.example.interview.controller;

import java.util.List;

import com.example.interview.controller.mapper.BookDtoMapper;
import com.example.interview.dto.BookDto;
import com.example.interview.model.Book;
import com.example.interview.service.BookService;
import com.example.interview.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final ReservationService reservationService;

    private final BookDtoMapper mapper;

    @GetMapping
    public List<BookDto> getBooks(@RequestParam(required = false, defaultValue = "false") final Boolean available) {
        final List<Book> books = available ? reservationService.getAvailableBooks() : bookService.getAll();
        return books.stream().map(mapper::map).toList();
    }
}

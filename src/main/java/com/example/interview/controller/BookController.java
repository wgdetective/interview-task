package com.example.interview.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.example.interview.dto.BookDto;
import com.example.interview.controller.mapper.BookDtoMapper;
import com.example.interview.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final BookDtoMapper mapper;

    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks().stream().map(mapper::map).collect(Collectors.toList());
    }
}

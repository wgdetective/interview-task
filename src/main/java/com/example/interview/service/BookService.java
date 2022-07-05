package com.example.interview.service;

import java.util.ArrayList;
import java.util.List;

import com.example.interview.model.Book;
import com.example.interview.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final List<BookRepository> bookRepositories;
    public List<Book> getAllBooks() {
        final List<Book> result = new ArrayList<>();
        bookRepositories.forEach(r -> result.addAll(r.findAll()));
        return result;
    }
}

package com.example.interview.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.interview.model.Book;
import com.example.interview.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final List<BookRepository> bookRepositories;

    public List<Book> getAll() {
        final List<Book> result = new ArrayList<>();
        bookRepositories.forEach(r -> result.addAll(r.findAll()));
        return result;
    }

    public Optional<Book> getById(final String id) {
        return bookRepositories.stream()
                .map(r -> r.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findAny();
    }
}

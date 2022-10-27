package com.example.interview.service;

import java.util.List;

import com.example.interview.model.Book;
import com.example.interview.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BookService {

    private final List<BookRepository> bookRepositories;

    public Flux<Book> getAll() {
        return Flux.fromIterable(bookRepositories)
                .flatMap(BookRepository::findAll);
    }

    public Mono<Book> getById(final String id) {
        return Flux.fromIterable(bookRepositories)
                .flatMap(r -> r.findById(id))
                .next();
    }
}

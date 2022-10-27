package com.example.interview.repository;

import com.example.interview.model.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookRepository {

    Flux<Book> findAll();

    Mono<Book> findById(final String id);
}

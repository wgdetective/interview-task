package com.example.interview.repository;

import java.util.List;
import java.util.Optional;

import com.example.interview.model.Book;

public interface BookRepository {

    List<Book> findAll();

    Optional<Book> findById(final String id);
}

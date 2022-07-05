package com.example.interview.repository;

import java.util.List;

import com.example.interview.model.Book;

public interface BookRepository {
    List<Book> findAll();
}

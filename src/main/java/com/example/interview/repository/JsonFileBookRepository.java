package com.example.interview.repository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.example.interview.entity.JsonBookEntity;
import com.example.interview.model.Book;
import com.example.interview.repository.mapper.JsonBookEntityMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JsonFileBookRepository implements BookRepository {

    private final static String BOOKS_FILE_NAME = "books.json";

    private final ObjectMapper objectMapper;

    private final JsonBookEntityMapper mapper;

    private List<JsonBookEntity> entities;

    @PostConstruct
    public void init() throws IOException {
        try (final var is = this.getClass().getClassLoader().getResourceAsStream(BOOKS_FILE_NAME)) {
            entities = objectMapper.readValue(is, new TypeReference<>() {

            });
        }
    }

    @Override
    public List<Book> findAll() {
        return entities.stream().map(mapper::map).toList();
    }

    @Override
    public Optional<Book> findById(final String id) {
        return entities.stream().filter(b -> b.getId().equals(id)).findAny().map(mapper::map);
    }
}

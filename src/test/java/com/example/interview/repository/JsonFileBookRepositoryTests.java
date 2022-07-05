package com.example.interview.repository;

import java.io.IOException;
import java.util.List;

import com.example.interview.model.Book;
import com.example.interview.repository.mapper.JsonBookEntityMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class JsonFileBookRepositoryTests {

    private JsonFileBookRepository repository;

    @BeforeEach
    public void init() throws IOException {
        repository = new JsonFileBookRepository(new ObjectMapper(), Mappers.getMapper(JsonBookEntityMapper.class));
        repository.init();
    }

    @Test
    public void testFindAll() {
        final var books = repository.findAll();
        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(3);
        for (Book book : books) {
            assertThat(book.getId()).isNotNull();
            assertThat(book.getAuthor()).isNotNull();
            assertThat(book.getName()).isNotNull();
            assertThat(book.getCopies()).isNotNull();
        }

    }

}

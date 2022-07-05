package com.example.interview.repository.mapper;

import com.example.interview.entity.JsonBookEntity;
import com.example.interview.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JsonBookEntityMapper {

    Book map(final JsonBookEntity book);
}

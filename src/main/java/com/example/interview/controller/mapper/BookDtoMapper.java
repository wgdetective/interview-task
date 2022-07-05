package com.example.interview.controller.mapper;

import com.example.interview.dto.BookDto;
import com.example.interview.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookDtoMapper {

    BookDto map(final Book book);
}

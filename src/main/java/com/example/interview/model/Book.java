package com.example.interview.model;

import lombok.Data;

@Data
public class Book {
    private String id;
    private String name;
    private String author;
    private Long copies;
}

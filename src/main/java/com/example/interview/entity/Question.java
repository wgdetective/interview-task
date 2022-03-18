package com.example.interview.entity;

import lombok.Data;

import java.util.List;

@Data
public class Question {

    private Long id;
    private String name;
    private List<String> answers;
}

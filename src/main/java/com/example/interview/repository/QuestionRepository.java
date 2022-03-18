package com.example.interview.repository;

import java.util.List;

import com.example.interview.entity.Question;

public interface QuestionRepository {

    List<Question> findAll();
}

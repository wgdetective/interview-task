package com.example.interview.dao;

import com.example.interview.entity.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> findAll();
}

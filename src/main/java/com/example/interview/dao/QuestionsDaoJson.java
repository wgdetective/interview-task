package com.example.interview.dao;

import com.example.interview.entity.Question;
import com.example.interview.exceptions.QuestionsNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class QuestionsDaoJson implements QuestionDao{

    @Override
    public Question getRandomQuestion() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            Question question = objectMapper.readValue(new File("questions.json"), Question.class);

        } catch (IOException e) {
            throw new QuestionsNotFoundException(e.getStackTrace(), "file not find");
        }
    }
}

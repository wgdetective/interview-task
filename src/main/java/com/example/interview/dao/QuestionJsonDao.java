package com.example.interview.dao;

import com.example.interview.entity.Question;
import com.example.interview.exceptions.QuestionsNotFoundException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Repository
public class QuestionJsonDao implements QuestionDao {

    @Override
    public List<Question> findAll() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("questions.json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(inputStream, new TypeReference<>(){});
        } catch (IOException e) {
            throw new QuestionsNotFoundException("File not found");
        }
    }
}


package com.example.interview.repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.example.interview.entity.Question;
import com.example.interview.exception.QuestionsNotFoundException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class QuestionsJsonRepository implements QuestionRepository {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Question> findAll() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("questions.json");
        try {
            return objectMapper.readValue(inputStream, new TypeReference<>() {

            });
        } catch (IOException e) {
            throw new QuestionsNotFoundException("File not found", e);
        }
    }
}

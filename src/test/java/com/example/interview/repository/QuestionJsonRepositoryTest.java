package com.example.interview.repository;

import java.util.List;

import com.example.interview.entity.Question;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionJsonRepositoryTest {

    @Test
    void findAll() {
        QuestionRepository questionDao = new QuestionsJsonRepository();
        List<Question> questions = questionDao.findAll();
        assertEquals(3, questions.size());
    }
}

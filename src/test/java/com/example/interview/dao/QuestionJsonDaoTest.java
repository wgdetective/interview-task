package com.example.interview.dao;

import com.example.interview.entity.Question;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionJsonDaoTest {

    @Test
    void findAll() {
        QuestionDao questionDao = new QuestionJsonDao();
        List<Question> questions = questionDao.findAll();
        assertEquals(questions.size(), 3);
    }
}
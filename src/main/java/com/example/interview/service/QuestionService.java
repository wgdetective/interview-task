package com.example.interview.service;

import com.example.interview.dao.QuestionDao;
import com.example.interview.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;

public class QuestionService {

    private final QuestionDao questionDao;

    @Autowired
    public QuestionService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }


    public Question getQuestion() {
        return questionDao.getRandomQuestion();
    }
}

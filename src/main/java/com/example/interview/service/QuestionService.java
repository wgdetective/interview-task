package com.example.interview.service;

import com.example.interview.dao.QuestionDao;
import com.example.interview.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionDao questionDao;

    public Question retrieveRandomQuestion() {
        List<Question> all = questionDao.findAll();
        Random random = new Random();
        return all.get(random.nextInt(all.size()));
    }
}

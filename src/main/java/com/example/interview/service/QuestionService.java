package com.example.interview.service;

import java.util.List;
import java.util.Random;

import com.example.interview.entity.Question;
import com.example.interview.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionDao;

    public Question retrieveRandomQuestion() {
        List<Question> all = questionDao.findAll();
        Random random = new Random();
        return all.get(random.nextInt(all.size()));
    }
}

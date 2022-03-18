package com.example.interview.controller;

import com.example.interview.entity.Question;
import com.example.interview.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/questions")
public class QuestionController {

    private final QuestionService service;

    @GetMapping("/random")
    public Question retrieveRandomQuestion() {
        return service.retrieveRandomQuestion();
    }

    ResponseEntity<?> sendAnswer() {
        return null;
    }

    ResponseEntity<?> getStatistics(){
        return null;
    }
}

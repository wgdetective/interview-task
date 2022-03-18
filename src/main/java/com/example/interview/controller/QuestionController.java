package com.example.interview.controller;

import com.example.interview.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
public class QuestionController {

    private final QuestionService service;

    @Autowired
    public QuestionController(QuestionService service) {
        this.service = service;
    }


    public ResponseEntity<?> getQuestion(){
        return ResponseEntity.ok(service.getQuestion());
    }

    ResponseEntity<?> sendAnswer() {
        return null;
    }

    ResponseEntity<?> getStatistics(){
        return null;
    }
}

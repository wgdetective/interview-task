package com.example.interview.controller;

import java.util.HashMap;
import java.util.Map;

import com.example.interview.exception.QuestionsNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {

    @ExceptionHandler(QuestionsNotFoundException.class)
    public ResponseEntity<Map<String, String>> questionsNotFoundException(Exception e) {
        Map<String, String> response = new HashMap<>();
        response.put("Error", e.getMessage());
        response.put("ErrorCode", HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}

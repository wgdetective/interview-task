package com.example.interview.exception;

public class QuestionsNotFoundException extends RuntimeException {

    public QuestionsNotFoundException(String msg, Throwable e) {
        super(msg, e);
    }
}

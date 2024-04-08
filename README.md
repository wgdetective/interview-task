# Interview task

## How to use it
In current repositories you could find some tasks that could be used for interviewing of candidates.

Different tasks and different stages could be found in different branches.

In general - each task is just writing of classical spring boot application with 3 layered architecture.
Candidate could use either maven either gradle build tool.
Using different snapshots of the same task you could also ask candidate to make code review of already written code.

Working on practical tasks is a great opportunity to see how candidate works, googles, writing (or not) tests, asking questions and etc.

For evaluation of candidates next form could be used: https://docs.google.com/spreadsheets/d/1dTSHLjoJZht2GeUgEhfN4wVBZWzJXYRfqIx9iVFdusA/edit?usp=sharing

## Task 1. Q&A

Rest application for conducting surveys.

1) As a User, I want to get random question
2) As a User, I want to answer question
3) As a User, I want to get statistics on questions that was answered by me

### Additional requirements:
* As a User, I want to get random question that wasn't answered by me
* As a User, I want to change my answer

### Additional architecture requirements:
* highload
* multiple sources of data
* security vulnerabilities

### JSON Datasource
```json
[
    {      
        "id" : 1,      
        "name" : "What is your favorite color?",
        "answers" : [
            "Black",
            "White",
            "Red",
            "Other"
        ]  
    },
    {
        "id" : 2,
        "name" : "Do you like this interview?",
        "answers" : [
            "Yes",
            "No",
            "Not sure"
        ]  
    },
    {
        "id" : 3,
        "name" : "Instagram or TikTok?",
        "answers" : [
            "Instagram",
            "TikTok",
            "Other"
        ]  
    }
]
```

### CVS Datasource
```cvs
"id","name","answers"
1,"What is your favorite color?","Black;White;Red;Other"
2,"Do you like this interview?","Yes;No;Not sure"
3,"Instagram or TikTok?","Instagram;TikTok;Other"
```

### XML source
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<root>
    <question>
        <id>1</id>
        <name>What is your favorite color?</name>
        <answers>
            <value>Black</value>
            <value>White</value>
            <value>Red</value>
            <value>Other</value>
        </answers>
    </question>
    <question>
        <id>2</id>
        <name>Do you like this interview?</name>
        <answers>
            <value>Yes</value>
            <value>No</value>
            <value>Not sure</value>
        </answers>
    </question>
    <question>
        <id>3</id>
        <name>Instagram or TikTok?</name>
        <answers>
            <value>Instagram</value>
            <value>TikTok</value>
            <value>Other</value>
        </answers>
    </question>
</root>
```

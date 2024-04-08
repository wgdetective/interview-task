# Interview task

## How to use it
In current repositories you could find some tasks that could be used for interviewing of candidates.

You can find various tasks in the current repository that can be used to interview candidates. These tasks are located in different branches and you also can find different stages of teh same task in the repo. Generally, each task involves writing a classic Spring Boot application with a 3-layer architecture. Candidates can use either Maven or Gradle as their build tool. By using different snapshots of the same task, you can also ask the candidate to review the code that has already been written.

Working on practical tasks provides an excellent opportunity to observe how the candidate works, searches for information (using of goolge or chatGPT isn't forbidden), writes tests, asks questions, and more.

For evaluating candidates, the following form can be used:
https://docs.google.com/spreadsheets/d/1dTSHLjoJZht2GeUgEhfN4wVBZWzJXYRfqIx9iVFdusA/edit?usp=sharing

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

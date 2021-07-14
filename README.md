# Interview task

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

# Interview task

## Task 2. Voting system

Rest application for voting in elections.

1) As a User, I want to get list of candidates 
2) As a User, I want to vote for the candidate
3) As a User, I want to get statistics on current status of elections

### Additional architecture requirements:
* highload
* multiple sources of data
* security vulnerabilities

### JSON Datasource
```json
[
    {      
        "id" : 1,      
        "name" : "Alexander the Eternal"
    },
    {
        "id" : 2,
        "name" : "Volodimir"
    },
    {
        "id" : 3,
        "name" : "Other"
    }
]
```

### CVS Datasource
```cvs
"id","name","answers"
1,"Alexander the Eternal"
2,"Volodimir"
3,"Other"
```

### XML source
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<root>
    <candidate>
        <id>1</id>
        <name>Alexander the Eternal</name>
    </candidate>
    <candidate>
        <id>2</id>
        <name>Volodimir</name>
    </candidate>
    <candidate>
        <id>3</id>
        <name>Other</name>
    </candidate>
</root>
```

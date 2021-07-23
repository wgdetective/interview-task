# Interview task

## Task 3. Library

Rest application for library.

1) As a User, I want to get list of available books
2) As a User, I want to book some books
3) As a User, I want to list of books that was booked by me

### Additional requirements:

* As a User, I want to return some of my books

### Additional architecture requirements:

* highload
* multiple sources of data
* security vulnerabilities

### JSON Datasource

```json
[
  {
    "id": 1,
    "name": "Harry Potter and the Philosopher's Stone",
    "author": "Joanne Rowling"
  },
  {
    "id": 2,
    "name": "The Lord of the Rings: The Fellowship of the Ring",
    "author" : "John Ronald Reuel Tolkien"
  },
  {
    "id": 3,
    "name": "Epam handbook",
    "author" : "Epam"
  }
]
```

[comment]: <> (### CVS Datasource)

[comment]: <> (```cvs)

[comment]: <> ("id","name","author")

[comment]: <> (1,"What is your favorite color?","Black;White;Red;Other")

[comment]: <> (2,"Do you like this interview?","Yes;No;Not sure")

[comment]: <> (3,"Instagram or TikTok?","Instagram;TikTok;Other")

[comment]: <> (```)

[comment]: <> (### XML source)

[comment]: <> (```xml)

[comment]: <> (<?xml version="1.0" encoding="UTF-8" ?>)

[comment]: <> (<root>)

[comment]: <> (    <question>)

[comment]: <> (        <id>1</id>)

[comment]: <> (        <name>What is your favorite color?</name>)

[comment]: <> (        <answers>)

[comment]: <> (            <value>Black</value>)

[comment]: <> (            <value>White</value>)

[comment]: <> (            <value>Red</value>)

[comment]: <> (            <value>Other</value>)

[comment]: <> (        </answers>)

[comment]: <> (    </question>)

[comment]: <> (    <question>)

[comment]: <> (        <id>2</id>)

[comment]: <> (        <name>Do you like this interview?</name>)

[comment]: <> (        <answers>)

[comment]: <> (            <value>Yes</value>)

[comment]: <> (            <value>No</value>)

[comment]: <> (            <value>Not sure</value>)

[comment]: <> (        </answers>)

[comment]: <> (    </question>)

[comment]: <> (    <question>)

[comment]: <> (        <id>3</id>)

[comment]: <> (        <name>Instagram or TikTok?</name>)

[comment]: <> (        <answers>)

[comment]: <> (            <value>Instagram</value>)

[comment]: <> (            <value>TikTok</value>)

[comment]: <> (            <value>Other</value>)

[comment]: <> (        </answers>)

[comment]: <> (    </question>)

[comment]: <> (</root>)

[comment]: <> (```)

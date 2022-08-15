# log-parser

To run the application you will need:
- Java 17
- Gradle 7.5
- PostgreSQL 14.4

In postgres you need to create user:
- username: logparser
- password: logparser

Database name: logparser with schema name: logparser owned by previously created user.

In the project's main directory run
``gradle bootRun`` and it should start the application.

The are three main http endpoints available:
- post method ``http://localhost:8080/entries`` - should contain plain text in request body containing log entries to parse
- get method ``http://localhost:8080/entries`` - returns all log entries
- get method ``http://localhost:8080/entries/search`` - takes query parameters like: dateFrom, dateTo, page, size, sortList, sortDirection and http header: Accept with value set to `application/json` or `application/xml`

There is a Postman collection available under JSON link: `https://www.getpostman.com/collections/37f8adef116fe18d9371`
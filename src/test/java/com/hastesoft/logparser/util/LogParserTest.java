package com.hastesoft.logparser.util;

import com.hastesoft.logparser.model.LogEntry;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LogParserTest {

    @Test
    void parseLogEntriesWhenSourceContainsOneLineThenReturnListWithOneElement() {
        String source =
                "2020-01-01 00:00:00.000 INFO  com.hastesoft.logparser.LogParser - This is a log entry";
        assertEquals(1, LogParser.parseLogEntries(source).size());
    }

    @Test
    void parseLineTest() {
        String source =
                "INFO 2019-10-23T10:12:35.000 com.example.demo.DemoApplication - Hello world!";
        LogEntry expected =
                new LogEntry(
                        null,
                        LocalDateTime.parse("2019-10-23T10:12:35.000"),
                        "INFO",
                        "com.example.demo.DemoApplication",
                        "- Hello world!");

        LogEntry actual = LogParser.parseLine(source);

        assertEquals(expected, actual);
    }
}
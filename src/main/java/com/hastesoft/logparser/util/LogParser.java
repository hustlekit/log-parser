package com.hastesoft.logparser.util;

import com.hastesoft.logparser.model.LogEntry;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogParser {

    public static List<LogEntry> parseLogEntries(String source) {
        List<LogEntry> result = new ArrayList<>();
        List<String> entryLines = Arrays.asList(source.split(System.lineSeparator()));

        entryLines.forEach((e) -> {
            LogEntry logEntry = parseLine(e);
            result.add(logEntry);
        });

        return result;
    }

    public static LogEntry parseLine(String source) {
        LogEntry result = new LogEntry();
        List<String> parts = Arrays.asList(source.split(" "));
        List<Integer> indexesToRemove = new ArrayList<>();

        boolean levelParsed = false;

        boolean dateTimeParsed = false;
        boolean dateParsed = false;
        boolean timeParsed = false;

        LocalDateTime localDateTime;
        LocalDate localDate = null;
        LocalTime localTime = null;

        for (int i = 0; i < parts.size(); i++) {

            if (!levelParsed) {
                if (StringUtils.isAllUpperCase(parts.get(i))) {
                    result.setLogLevel(parts.get(i));
                    levelParsed = true;
                    indexesToRemove.add(i);
                }
            }

            if (!dateTimeParsed) {
                try {
                    localDateTime = LocalDateTime.parse(parts.get(i));
                    result.setEventDate(localDateTime);
                    dateTimeParsed = true;
                } catch (Exception e) {
                    if (!dateParsed) {
                        try {
                            localDate = LocalDate.parse(parts.get(i));
                            dateParsed = true;
                        } catch (Exception ignored) {}
                    }
                    if (!timeParsed) {
                        try {
                            localTime = LocalTime.parse(parts.get(i));
                            timeParsed = true;
                        } catch (Exception ignored) {}
                    }
                }
                if (!dateTimeParsed && (dateParsed && timeParsed)) {
                    try {
                        localDateTime = LocalDateTime.of(localDate, localTime);
                        result.setEventDate(localDateTime);
                        dateTimeParsed = true;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }

        return result;
    }
}

package com.hastesoft.logparser.util;

import com.hastesoft.logparser.model.LogEntry;
import org.apache.commons.lang3.StringUtils;

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

        for (int i = 0; i < parts.size(); i++) {
            if (!levelParsed) {
                if (StringUtils.isAllUpperCase(parts.get(i))) {
                    result.setLogLevel(parts.get(i));
                    levelParsed = true;
                    indexesToRemove.add(i);
                }
            }
        }

        return result;
    }
}

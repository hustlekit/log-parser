package com.hastesoft.logparser.service;

import com.hastesoft.logparser.model.LogEntry;
import com.hastesoft.logparser.repository.LogEntryRepository;
import com.hastesoft.logparser.util.LogParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogEntryService {

    @Autowired
    private LogEntryRepository logEntryRepository;

    public List<LogEntry> parseAndSaveLog(String source) {
        return logEntryRepository.saveAll(LogParser.parseLogEntries(source));
    }
}

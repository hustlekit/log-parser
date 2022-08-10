package com.hastesoft.logparser.controller;

import com.hastesoft.logparser.model.LogEntry;
import com.hastesoft.logparser.service.LogEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/entries")
@RestController
public class LogEntryController {

    @Autowired
    private LogEntryService logEntryService;

    @PostMapping
    public ResponseEntity<List<LogEntry>> parseAndSaveLog(@RequestBody String source) {
        List<LogEntry> result = logEntryService.parseAndSaveLog(source);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

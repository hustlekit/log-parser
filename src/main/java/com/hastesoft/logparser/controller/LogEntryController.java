package com.hastesoft.logparser.controller;

import com.hastesoft.logparser.model.LogEntry;
import com.hastesoft.logparser.service.LogEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<LogEntry>> getAll() {
        List<LogEntry> result = logEntryService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

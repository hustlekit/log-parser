package com.hastesoft.logparser.controller;

import com.hastesoft.logparser.model.LogEntry;
import com.hastesoft.logparser.service.LogEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RequestMapping("/entries")
@RestController
public class LogEntryController {

    @Autowired
    private LogEntryService logEntryService;

    @PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<List<LogEntry>> parseAndSaveLog(@RequestBody String source) {
        List<LogEntry> result = logEntryService.parseAndSaveLog(source);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<LogEntry>> getAll() {
        List<LogEntry> result = logEntryService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path = "/search", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Page<LogEntry>> getLogEntriesWithDateFilterAndPagination(
            @RequestParam(defaultValue = "") String dateFrom,
            @RequestParam(defaultValue = "") String dateTo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") List<String> sortList,
            @RequestParam(defaultValue = "DESC") Sort.Direction sortDirection
            ) {
        LocalDateTime dateFromFilter = !dateFrom.isEmpty() ? LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("dd-MM-yyyy")).atStartOfDay() : null;
        LocalDateTime dateToFilter = !dateTo.isEmpty() ? LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("dd-MM-yyyy")).atStartOfDay() : null;
        Page<LogEntry> logEntryPage = logEntryService.getLogEntriesAsPageWithDateFilteringAndSorting(dateFromFilter, dateToFilter, page, size, sortList, sortDirection.toString());
        return new ResponseEntity<>(logEntryPage, HttpStatus.OK);
    }
}

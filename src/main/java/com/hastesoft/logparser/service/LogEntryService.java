package com.hastesoft.logparser.service;

import com.hastesoft.logparser.model.LogEntry;
import com.hastesoft.logparser.repository.LogEntryRepository;
import com.hastesoft.logparser.util.LogParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LogEntryService {

    @Autowired
    private LogEntryRepository logEntryRepository;

    public List<LogEntry> parseAndSaveLog(String source) {
        return logEntryRepository.saveAll(LogParser.parseLogEntries(source));
    }

    public List<LogEntry> getAll() {
        return logEntryRepository.findAll();
    }

    public Page<LogEntry> getLogEntriesAsPageWithDateFilteringAndSorting(
            LocalDateTime dateFromFilter,
            LocalDateTime dateToFilter,
            int page,
            int size,
            List<String> sortList,
            String sortOrder
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(createSortOrder(sortList, sortOrder)));
        Page<LogEntry> result = null;
        if (dateFromFilter != null && dateToFilter != null) {
            result = logEntryRepository.findByDateFromAndDateToFilter(dateFromFilter, dateToFilter, pageable);
        }
        if (dateFromFilter == null) {
            result = logEntryRepository.findByDateToFilter(dateToFilter, pageable);
        }
        if (dateToFilter == null) {
            result = logEntryRepository.findByDateFromFilter(dateFromFilter, pageable);
        }
        if (dateFromFilter == null && dateToFilter == null) {
            result = logEntryRepository.findAllPageable(pageable);
        }
        return result;
    }

    private List<Sort.Order> createSortOrder(List<String> sortList, String sortDirection) {
        List<Sort.Order> sorts = new ArrayList<>();
        Sort.Direction direction;
        for (String sort : sortList) {
            if (sortDirection != null) {
                direction = Sort.Direction.fromString(sortDirection);
            } else {
                direction = Sort.Direction.DESC;
            }
            sorts.add(new Sort.Order(direction, sort));
        }
        return sorts;
    }
}

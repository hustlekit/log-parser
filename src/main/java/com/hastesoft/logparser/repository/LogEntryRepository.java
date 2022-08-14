package com.hastesoft.logparser.repository;

import com.hastesoft.logparser.model.LogEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface LogEntryRepository extends JpaRepository<LogEntry, Long> {

    @Query("select l from LogEntry l where l.eventDate >= :dateFrom and l.eventDate < :dateTo")
    Page<LogEntry> findByDateFromAndDateToFilter(
            @Param("dateFrom") LocalDateTime dateFrom,
            @Param("dateTo") LocalDateTime dateTo,
            Pageable pageable);

    @Query("select l from LogEntry l where l.eventDate < :dateTo")
    Page<LogEntry> findByDateToFilter(@Param("dateTo") LocalDateTime dateTo, Pageable pageable);

    @Query("select l from LogEntry l where l.eventDate >= :dateFrom")
    Page<LogEntry> findByDateFromFilter(@Param("dateFrom") LocalDateTime dateFrom, Pageable pageable);

    @Query("select l from LogEntry l")
    Page<LogEntry> findAllPageable(Pageable pageable);
}

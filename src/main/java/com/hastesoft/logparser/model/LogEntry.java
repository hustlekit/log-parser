package com.hastesoft.logparser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "log_entry")
@SequenceGenerator(name = "logEntryGenerator", sequenceName = "log_entry_seq")
public class LogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "logEntryGenerator")
    private Long id;
    private LocalDateTime eventDate;
    private String logLevel;
    private String invokingClass;
    private String content;
}

package io.pivotal.pal.tracker;

import org.springframework.http.ResponseEntity;

import java.util.List;

public class TimeEntryController {
    TimeEntryRepository timeEntryRepository;


    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    public ResponseEntity create(TimeEntry timeEntryToCreate) {
    }

    public ResponseEntity<TimeEntry> delete(long l) {
    }

    public ResponseEntity update(long l, TimeEntry expected) {
    }

    public ResponseEntity<List<TimeEntry>> list() {
    }

    public ResponseEntity<TimeEntry> read(long l) {
    }
}

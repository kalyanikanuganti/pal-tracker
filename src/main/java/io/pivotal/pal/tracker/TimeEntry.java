package io.pivotal.pal.tracker;

import java.time.LocalDate;

public class TimeEntry {
    private long id;
    private long projectId;
    private long userId;
    private LocalDate date;
    private int hours;

    public TimeEntry(long l, long l1, LocalDate parse, int i) {
    }

    public TimeEntry(long l, long l1, long l2, LocalDate parse, int i) {
    }

    public long getId() {
        return id;
    }
}

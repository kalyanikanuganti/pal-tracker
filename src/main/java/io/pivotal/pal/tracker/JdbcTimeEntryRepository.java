package io.pivotal.pal.tracker;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JdbcTimeEntryRepository implements TimeEntryRepository {

    HashMap<Long,TimeEntry> timeEntryMap  = new HashMap<>();
    DataSource dataSource;

    public JdbcTimeEntryRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }



    private long sequence = 1;
    public TimeEntry create(TimeEntry timeEntry) {
        TimeEntry timeEntryLoc = new TimeEntry(sequence++, timeEntry.getProjectId(), timeEntry.getUserId(),timeEntry.getDate(),timeEntry.getHours());
        timeEntryMap.put(timeEntryLoc.getId(), timeEntryLoc);
        return timeEntryLoc;
    }

    public TimeEntry find(long l) {
        return timeEntryMap.get(l);

    }


    public TimeEntry update(long id, TimeEntry timeEntry) {
        timeEntry.setId(id);
        timeEntryMap.put(id,timeEntry);
        return timeEntryMap.get(id) ;
    }

    public void delete(long id) {
        timeEntryMap.remove(id);
    }

    public List<TimeEntry> list() {
        ArrayList<TimeEntry> timeEntryList = new ArrayList<> (timeEntryMap.values());
        return timeEntryList;
    }
}

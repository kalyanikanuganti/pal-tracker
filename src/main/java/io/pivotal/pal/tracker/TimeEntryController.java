package io.pivotal.pal.tracker;

import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    TimeEntryRepository timeEntriesRepo;
    private final CounterService counter;
    private final GaugeService gauge;


    public TimeEntryController(TimeEntryRepository timeEntryRepository,
                               CounterService counter, GaugeService gauge) {

        this.timeEntriesRepo = timeEntryRepository;
        this.counter = counter;
        this.gauge = gauge;
    }


    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry createdTimeEntry = timeEntriesRepo.create(timeEntryToCreate);

        if (createdTimeEntry != null) {
            counter.increment("TimeEntry.created");
            gauge.submit("timeEntries.count", timeEntriesRepo.list().size());
            return new ResponseEntity<>(createdTimeEntry, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry createdTimeEntry = timeEntriesRepo.find(id);
        if (createdTimeEntry != null) {
            counter.increment("TimeEntry.read");
            return new ResponseEntity<>(createdTimeEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        counter.increment("TimeEntry.listed");
        return new ResponseEntity<>(timeEntriesRepo.list(), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody TimeEntry timeEntry) {
        TimeEntry updatedTimeEntry = timeEntriesRepo.update(id, timeEntry);
        if (updatedTimeEntry != null) {
            counter.increment("TimeEntry.updated");
            return new ResponseEntity<>(updatedTimeEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long id) {

        //   if(timeEntriesRepo.find(id)!=null){
        timeEntriesRepo.delete(id);
        counter.increment("TimeEntry.deleted");
        gauge.submit("timeEntries.count", timeEntriesRepo.list().size());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        //     }else{
        //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        //  }
    }
}

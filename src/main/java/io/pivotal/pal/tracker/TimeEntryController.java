package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepo) {
        timeEntryRepository = timeEntryRepo;
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {

        TimeEntry createdTimeEntry = timeEntryRepository.create(timeEntryToCreate);
        return new ResponseEntity<>(createdTimeEntry, HttpStatus.CREATED);

    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long id) {
        TimeEntry entry = timeEntryRepository.find(id);
        ResponseEntity<TimeEntry> responseEntity;
        if(entry != null){
            responseEntity = new ResponseEntity<TimeEntry>(entry, HttpStatus.OK);
        }else{
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  responseEntity;
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        return  new ResponseEntity<List<TimeEntry>>(timeEntryRepository.list(), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody TimeEntry expected) {

        TimeEntry entry = timeEntryRepository.update(id, expected);
        ResponseEntity<TimeEntry> responseEntity;
        if(entry != null){
            responseEntity = new ResponseEntity<TimeEntry>(entry, HttpStatus.OK);
        }else{
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  responseEntity;
    }


    @DeleteMapping("{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable Long id) {

        timeEntryRepository.delete(id);

        return new ResponseEntity<TimeEntry>(HttpStatus.NO_CONTENT);
    }


}

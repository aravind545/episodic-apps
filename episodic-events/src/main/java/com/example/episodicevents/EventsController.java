package com.example.episodicevents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by trainer9 on 5/22/17.
 */
@RestController
public class EventsController {


    public EventsController(EventsRepository eventsRepo) {
        this.eventsRepo = eventsRepo;
    }

    @Autowired
    EventsRepository eventsRepo;

    @GetMapping("/recent")
        public List<Event> getEvents() {
        PageRequest pageRequest = new PageRequest(0,20, new Sort(Sort.Direction.DESC, "createdAt"));
        return eventsRepo.findRecent(pageRequest);
    }

    @PostMapping("/")
    public Object createEvent(@RequestBody Event event) {
        eventsRepo.save(event);
                return event;
    }
}

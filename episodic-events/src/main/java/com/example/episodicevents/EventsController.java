package com.example.episodicevents;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

/**
 * Created by trainer9 on 5/22/17.
 */
@RestController
public class EventsController {

    private final RabbitTemplate rabbitTemplate;



    @PostMapping
    public void publishEvent(@RequestBody String body){
        rabbitTemplate.convertAndSend("my-exchange", "my-routing-key", body);
    }


    public EventsController(EventsRepository eventsRepo, RabbitTemplate rabbitTemplate) {
        this.eventsRepo = eventsRepo;
        this.rabbitTemplate=rabbitTemplate;
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
        if(event instanceof ProgressEvent)
        {
            Instant instant = event.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant();
            rabbitTemplate.convertAndSend("my-exchange", "my-routing-key", new ProgressMessage(event.getUserId()
                    ,event.getEpisodeId(),
                    Date.from(instant),((ProgressEvent) event).getData().getOffset()));


        }
        return event;
    }
}

package com.example.episodicevents;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Created by trainer9 on 5/22/17.
 */
public interface EventsRepository extends MongoRepository<Event,String> {

    @Query("{}")
    List<Event> findRecent(PageRequest pageRequest);
}

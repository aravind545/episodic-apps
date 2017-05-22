package com.example.episodicevents;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by trainer9 on 5/22/17.
 */
public interface EventsRepository extends MongoRepository<Event,String> {
}

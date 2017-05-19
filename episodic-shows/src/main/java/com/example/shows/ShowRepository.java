package com.example.shows;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by trainer9 on 5/17/17.
 */
public interface ShowRepository extends CrudRepository<Show, Long> {
    Show findById(Long id);

}

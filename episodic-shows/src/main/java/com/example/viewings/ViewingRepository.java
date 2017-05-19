package com.example.viewings;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by trainer9 on 5/17/17.
 */
public interface ViewingRepository extends CrudRepository<Viewing, Long> {

    @Modifying
    @Query("update Viewing v set v.episodeId = ?1 , v.updatedAt =?2 , v.timecode = ?3 where v.userId = ?4")
    @Transactional
    Integer updateViewingEpisodeTimeCodebyUserId(Long episodeId, Date updatedAt, int timecode, Long userId);

    List<Viewing> findAllByUserIdOrderByUpdatedAt(Long userId);


}

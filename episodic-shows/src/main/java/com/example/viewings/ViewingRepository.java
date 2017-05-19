package com.example.viewings;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by trainer9 on 5/17/17.
 */
public interface ViewingRepository extends CrudRepository<Viewing, Long> {

    @Modifying
    @Query("update Viewing v set v.episode_id = ?1 , v.updated_at =?2 , v.timecode = ?3 where v.user_id = ?4")
    @Transactional
    Integer updateViewingEpisodeTimeCodebyUserId(Long episodeId, Date updatedAt, int timecode, Long userId);


    Viewing findByUser_id(Long user_id);


}

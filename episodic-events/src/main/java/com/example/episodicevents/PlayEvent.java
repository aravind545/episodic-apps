package com.example.episodicevents;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by trainer9 on 5/22/17.
 */
@JsonTypeName("play")
@Getter
@Setter
@NoArgsConstructor
public class PlayEvent extends Event {

    private Data data;

    public PlayEvent(Long userId, Long showId, Long episodeId, Date createdAt, Data data) {

        super(userId, showId, episodeId, createdAt);
        this.data=data;
    }
}

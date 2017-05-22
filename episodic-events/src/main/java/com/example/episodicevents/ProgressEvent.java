package com.example.episodicevents;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Created by trainer9 on 5/22/17.
 */
@JsonTypeName("progress")
@Getter
@Setter
@NoArgsConstructor
public class ProgressEvent extends Event {

    private Data data;
    public ProgressEvent(Long userId, Long showId, Long episodeId, LocalDateTime createdAt, Data data) {

        super( userId, showId, episodeId, createdAt);
        this.data=data;
    }
}

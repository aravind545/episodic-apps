package com.example.viewings;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by trainer9 on 5/23/17.
 */
@Getter
@Setter
@NoArgsConstructor
public class Progress {

    Long userId;
    Long episodeId;
    Date createdAt;
    int offset;

    public Progress(Long userId, Long episodeId, Date createdAt, int offset) {
        this.userId = userId;
        this.episodeId = episodeId;
        this.createdAt = createdAt;
        this.offset = offset;
    }



}

package com.example.episodicevents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by trainer9 on 5/24/17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgressMessage {

    private Long userId;
    private Long episodeId;
    private Date createdAt;
    int offset;
}

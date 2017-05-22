package com.example.episodicevents;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by trainer9 on 5/22/17.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible=true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PlayEvent.class, name = "play"),
        @JsonSubTypes.Type(value = PauseEvent.class, name = "pause"),
        @JsonSubTypes.Type(value = FastForwardEvent.class, name = "fastForward"),
        @JsonSubTypes.Type(value = RewindEvent.class, name = "rewind"),
        @JsonSubTypes.Type(value = ProgressEvent.class, name = "progress"),
        @JsonSubTypes.Type(value = ScrubEvent.class, name = "scrub")
})


@NoArgsConstructor
public abstract class Event {



    private Long userId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public Long getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(Long episodeId) {
        this.episodeId = episodeId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    private Long showId;
    private Long episodeId;
    private LocalDateTime createdAt;

    public Event(Long userId, Long showId, Long episodeId, LocalDateTime createdAt) {

        this.userId = userId;
        this.showId = showId;
        this.episodeId = episodeId;
        this.createdAt = createdAt;
    }
}

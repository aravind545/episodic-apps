package com.example.episodes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by trainer9 on 5/17/17.
 */

@Entity
@Table(name="episodes")
@Getter
@Setter
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @JsonIgnore
    Long show_id;

    @JsonProperty("seasonNumber")
    int season_number;

    @JsonProperty("episodeNumber")
    int episode_number;

    @Transient
    String title;

}

package com.example.viewings;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by trainer9 on 5/17/17.
 */

@Entity
@Table(name="viewings")
@Getter
@Setter
public class Viewing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    Long user_id;

    Long show_id;

    @JsonProperty("episodeId")
    Long episode_id;

    int timecode;

    @JsonProperty("updatedAt")
    Date updated_at;

}

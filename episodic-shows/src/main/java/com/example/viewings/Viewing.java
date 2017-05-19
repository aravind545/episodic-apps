package com.example.viewings;

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

    @Column(name="user_id")
    Long userId;

    @Column(name="show_id")
    Long showId;

    @Column(name="episode_id")
    Long episodeId;

    int timecode;

    @Column(name="updated_at")
    Date updatedAt;

}

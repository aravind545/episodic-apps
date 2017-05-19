package com.example.viewings;

import com.example.episodes.Episode;
import com.example.shows.Show;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by trainer9 on 5/19/17.
 */
@Getter
@Setter
public class Show_Episode_By_User {

    Show show;
    Episode episode;
    Date updatedAt;
    int timecode;

}

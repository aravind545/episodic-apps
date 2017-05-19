package com.example.viewings;

import com.example.shows.ShowRepository;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trainer9 on 5/17/17.
 */
@RestController
@RequestMapping("/users")
public class ViewingsController {



    private final ViewingRepository viewingRepo;

    private final ShowRepository showRepo;

    public ViewingsController(ViewingRepository viewingRepo, ShowRepository showRepo) {

        Assert.notNull(viewingRepo, "ViewingRepo cannot be null");

        Assert.notNull(showRepo, "ShowRepository cannot be null");

        this.viewingRepo = viewingRepo;
        this.showRepo = showRepo;

    }


    @PostMapping("/{id}/viewings")
    public void createViewing(@RequestBody Viewing viewing, @PathVariable("id") String userId) {

        viewing.setUser_id(Long.parseLong(userId));

        //Take userId and get show from show repository.
        this.viewingRepo.updateViewingEpisodeTimeCodebyUserId(viewing.getEpisode_id(),
                viewing.getUpdated_at(),viewing.getTimecode(),Long.parseLong(userId));

    }

    @GetMapping("{id}/recently-watched")
    public List<Show_Episode_By_User> getRecentlyWatchedShow_EpisodeByUser( @PathVariable("id") String userId) {

        List<Show_Episode_By_User> allShowEpisodesByUser = new ArrayList<Show_Episode_By_User>();

        //Now build the ShowEpisodeByUser object

        Viewing returnedViewing = this.viewingRepo.findByUser_id(Long.parseLong(userId));

        Show_Episode_By_User show_episode_by_user = new Show_Episode_By_User();

        show_episode_by_user.setUpdatedAt(returnedViewing.getUpdated_at());
        show_episode_by_user.setTimecode(returnedViewing.getTimecode());
//
//        for(Episode episode: allEpisodes)
//        {
//            episode.setTitle("S"+episode.getSeason_number()+" E"+episode.getEpisode_number());
//        }
        return allShowEpisodesByUser;
    }
}

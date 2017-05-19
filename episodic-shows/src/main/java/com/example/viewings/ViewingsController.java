package com.example.viewings;

import com.example.episodes.Episode;
import com.example.episodes.EpisodeRepository;
import com.example.shows.Show;
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

    private final EpisodeRepository episodeRepo;

    public ViewingsController(ViewingRepository viewingRepo, ShowRepository showRepo, EpisodeRepository episodeRepo) {

        Assert.notNull(viewingRepo, "ViewingRepo cannot be null");

        Assert.notNull(showRepo, "ShowRepository cannot be null");
        Assert.notNull(episodeRepo, "EpisodeRepository cannot be null");

        this.viewingRepo = viewingRepo;
        this.showRepo = showRepo;
        this.episodeRepo=episodeRepo;

    }


    @PatchMapping("/{id}/viewings")
    public void patchViewing(@RequestBody Viewing viewing, @PathVariable("id") String userId) {

        viewing.setUserId(Long.parseLong(userId));

        //Take userId and get show from show repository.
        this.viewingRepo.updateViewingEpisodeTimeCodebyUserId(viewing.getEpisodeId(),
                viewing.getUpdatedAt(),viewing.getTimecode(),Long.parseLong(userId));

    }


    @PostMapping("/{id}/viewings")
    public void createViewing(@RequestBody Viewing viewing, @PathVariable("id") String userId) {

        viewing.setUserId(Long.parseLong(userId));

        //Take userId and get show from show repository.
        this.viewingRepo.save(viewing);

    }

    @GetMapping("{id}/recently-watched")
    public List<Show_Episode_By_User> getRecentlyWatchedShow_EpisodeByUser( @PathVariable("id") String userId) {

        List<Show_Episode_By_User> allShowEpisodesByUser = new ArrayList<Show_Episode_By_User>();

        //Now build the ShowEpisodeByUser object

        List<Viewing> returnedViewingList = this.viewingRepo.findAllByUserIdOrderByUpdatedAt(Long.parseLong(userId));

        Show_Episode_By_User show_episode_by_user = new Show_Episode_By_User();



         Show returnedShow = showRepo.findById(returnedViewingList.get(0).getShowId());

        show_episode_by_user.setShow(returnedShow);

        Episode returnedEpisode = episodeRepo.findByShowId(returnedViewingList.get(0).getShowId());

        returnedEpisode.setTitle("S"+returnedEpisode.getShowId() + " E"+returnedEpisode.getSeasonNumber());

        show_episode_by_user.setShow(returnedShow);
        show_episode_by_user.setEpisode(returnedEpisode);

        show_episode_by_user.setUpdatedAt(returnedViewingList.get(0).getUpdatedAt());
        show_episode_by_user.setTimecode(returnedViewingList.get(0).getTimecode());

        allShowEpisodesByUser.add(show_episode_by_user);

        return allShowEpisodesByUser;
    }
}

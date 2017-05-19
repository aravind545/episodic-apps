package com.example.episodes;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by trainer9 on 5/17/17.
 */
@RestController
@RequestMapping("/shows")
public class EpisodesController {



    private final EpisodeRepository episodeRepo;

    public EpisodesController(EpisodeRepository episodeRepo) {

        Assert.notNull(episodeRepo, "EpisodeRepo cannot be null");

        this.episodeRepo = episodeRepo;

    }


    @PostMapping("{id}/episodes")
    public Episode createShow(@RequestBody Episode episode, @PathVariable(value="id") String showid) {

        episode.setShow_id(Long.parseLong(showid));
        Episode savedEpisode = episodeRepo.save(episode);
        savedEpisode.setTitle("S"+savedEpisode.getSeason_number()+" E"+savedEpisode.getEpisode_number());
        return savedEpisode;
    }

    @GetMapping("{id}/episodes")
    public List<Episode> getAllEpisodes( @PathVariable(value="id") String showid) {
        List<Episode> allEpisodes = (List<Episode>) episodeRepo.findAll();

        for(Episode episode: allEpisodes)
        {
            episode.setTitle("S"+episode.getSeason_number()+" E"+episode.getEpisode_number());
        }
        return allEpisodes;
    }
}

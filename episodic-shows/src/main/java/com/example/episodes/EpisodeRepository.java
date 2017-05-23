package com.example.episodes;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by trainer9 on 5/17/17.
 */
public interface EpisodeRepository extends CrudRepository<Episode, Long> {

    Episode findByShowId(Long showId);

    Episode findByEpisodeNumber(int episodeNumber);
}

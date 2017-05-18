package com.example.shows;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trainer9 on 5/17/17.
 */
@RestController
@RequestMapping("/shows")
public class ShowsController {

    private final ShowRepository ShowRepo;

    public ShowsController(ShowRepository ShowRepo) {
        Assert.notNull(ShowRepo, "ShowRepo cannot be null");
        this.ShowRepo = ShowRepo;

    }

    @RequestMapping("")
    public List<Show> getShows()
    {

        List<Show> allShows = new ArrayList<Show>();
        return (List<Show>) this.ShowRepo.findAll();



    }

    @PostMapping("")
    public Show createShow(@RequestBody Show show)
    {
        System.out.println(show.toString());
        return this.ShowRepo.save(show);

    }
}

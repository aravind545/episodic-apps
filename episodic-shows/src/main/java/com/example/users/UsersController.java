package com.example.users;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by trainer9 on 5/17/17.
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserRepository userRepo;

    public UsersController(UserRepository userRepo) {
        Assert.notNull(userRepo, "userRepo cannot be null");
        this.userRepo = userRepo;

    }

    @PostMapping("")
    public User createUser(@RequestBody User user)
    {
        System.out.println(user.toString());
        return this.userRepo.save(user);

    }
}

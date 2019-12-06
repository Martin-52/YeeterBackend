package com.yeeter.web.YeeterWebBackend.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.yeeter.web.YeeterWebBackend.service.UserService;
import com.yeeter.web.YeeterWebBackend.service.YeetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    YeetService yeetService;

    @Autowired
    UserService userService;

    @GetMapping("/user/uploadUser")
    public boolean uploadUser(@RequestParam String username, @RequestParam String uid)
            throws InterruptedException, FirebaseAuthException {
        return userService.uploadUser(username, uid);
    }

    @GetMapping("/user/usernameExists")
    public boolean usernameExists(@RequestParam String username) throws InterruptedException {
        return userService.usernameExists(username);
    }

    @PostMapping("/user/followUser")
    public void followUser(@RequestParam String username, @RequestParam String uid) throws InterruptedException {
        userService.followUser(username, uid);
    }

    @PostMapping("/user/unfollowUser")
    public void unfollowUser(@RequestParam String username, @RequestParam String uid) throws InterruptedException {
        userService.unfollowUser(username, uid);
    }

    @GetMapping("/user/isFollowingUser")
    public boolean isFollowingUser(@RequestParam String username, @RequestParam String uid) throws InterruptedException {
        return userService.isFollowingUser(username, uid);
    }
}
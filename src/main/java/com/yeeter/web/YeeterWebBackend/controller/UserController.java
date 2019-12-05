package com.yeeter.web.YeeterWebBackend.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.yeeter.web.YeeterWebBackend.service.UserService;
import com.yeeter.web.YeeterWebBackend.service.YeetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    YeetService yeetService;

    @Autowired
    UserService userService;

    @GetMapping("/user/uploadUser")
    public boolean uploadUser(@PathVariable String username, @PathVariable String uid)
            throws InterruptedException, FirebaseAuthException {
        return userService.uploadUser(username, uid);
    }

    @GetMapping("/user/usernameExists")
    public boolean usernameExists(@PathVariable String username) throws InterruptedException {
        return userService.usernameExists(username);
    }

    @GetMapping("/user/followUser")
    public boolean followUser(@PathVariable String username, @PathVariable String uid) throws InterruptedException {
        return userService.followUser(username, uid);
    }

    @GetMapping("/user/unfollowUser")
    public boolean unfollowUser(@PathVariable String username, @PathVariable String uid) throws InterruptedException {
        return userService.unfollowUser(username, uid);
    }

    @GetMapping("/user/isFollowingUser")
    public boolean isFollowingUser(@PathVariable String username, @PathVariable String uid) throws InterruptedException {
        return userService.isFollowingUser(username, uid);
    }
}
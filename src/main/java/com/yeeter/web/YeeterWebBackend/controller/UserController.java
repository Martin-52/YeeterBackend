package com.yeeter.web.YeeterWebBackend.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.yeeter.web.YeeterWebBackend.service.UserService;
import com.yeeter.web.YeeterWebBackend.service.YeetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class UserController {
    @Autowired
    YeetService yeetService;

    @Autowired
    UserService userService;

    @PostMapping("/user/uploadUser")
    public void uploadUser(@RequestParam("username") String username, @RequestParam("uid") String uid)
            throws InterruptedException, FirebaseAuthException {
        userService.uploadUser(username, uid);
    }

    @GetMapping("/user/usernameExists")
    public boolean usernameExists(@RequestParam("username") String username) throws InterruptedException {
        return userService.usernameExists(username);
    }

    @PostMapping("/user/followUser")
    public void followUser(@RequestParam("username") String username, @RequestParam("uid") String uid) throws InterruptedException {
        userService.followUser(username, uid);
    }

    @PostMapping("/user/unfollowUser")
    public void unfollowUser(@RequestParam("username") String username, @RequestParam("uid") String uid) throws InterruptedException {
        userService.unfollowUser(username, uid);
    }

    @GetMapping("/user/isFollowingUser")
    public boolean isFollowingUser(@RequestParam("username") String username, @RequestParam("uid") String uid) throws InterruptedException {
        return userService.isFollowingUser(username, uid);
    }
}
package com.yeeter.web.YeeterWebBackend.controller;

import com.yeeter.web.YeeterWebBackend.service.YeetService;

import java.util.List;

import com.google.firebase.auth.FirebaseAuthException;
import com.yeeter.web.YeeterWebBackend.model.Yeet;
import com.yeeter.web.YeeterWebBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class YeetController {
    @Autowired
    YeetService yeetService;

    @Autowired
    UserService userService;

    @PostMapping("/yeet/postYeet")
    public void postYeet(@RequestParam String post, @RequestParam String uid, @RequestParam String username)
            throws InterruptedException, FirebaseAuthException {
        yeetService.postYeet(post, uid, username);
    }

    @PostMapping("/yeet/likeDislike")
    public boolean likeDislike(@RequestParam String yeetKey, @RequestParam String uid)
            throws InterruptedException, FirebaseAuthException {
        return yeetService.likeDislike(yeetKey, uid);
    }

    @PostMapping("/yeet/addLike")
    public void addLike(@RequestParam String yeetKey, @RequestParam String uid)
            throws InterruptedException, FirebaseAuthException {
        yeetService.addLike(yeetKey, uid);
        yeetService.addUserToLikeList(yeetKey, uid);
    }

    @PostMapping("/yeet/removeLike")
    public void removeLike(@RequestParam String yeetKey, @RequestParam String uid)
            throws InterruptedException, FirebaseAuthException {
        yeetService.removeLike(yeetKey, uid);
        yeetService.removeUserFromLikeList(yeetKey, uid);
    }

    @PostMapping("/yeet/addDislike")
    public void addDislike(@RequestParam String yeetKey, @RequestParam String uid)
            throws InterruptedException, FirebaseAuthException {
        yeetService.addDislike(yeetKey, uid);
        yeetService.addUserToDislikeList(yeetKey, uid);
    }

    @PostMapping("/yeet/removeDislike")
    public void removeDislike(@RequestParam String yeetKey, @RequestParam String uid)
            throws InterruptedException, FirebaseAuthException {
        yeetService.removeDislike(yeetKey, uid);
        yeetService.removeUserFromDislikeList(yeetKey, uid);
    }

    @GetMapping("/yeet/personalPosts")
    public List<Yeet> personalPosts(@RequestParam String uid)
            throws InterruptedException, FirebaseAuthException {
        return yeetService.getPersonalPosts(uid);
    }

    @GetMapping("/yeet/followingPosts")
    public List<Yeet> followingPosts(@RequestParam String uid)
            throws InterruptedException, FirebaseAuthException {
        return yeetService.getFollowingPosts(uid);
    }
}
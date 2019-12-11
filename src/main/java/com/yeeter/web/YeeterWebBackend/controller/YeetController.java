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
    public void postYeet(@RequestParam("post") String post, @RequestParam("uid") String uid, @RequestParam("username") String username)
            throws InterruptedException, FirebaseAuthException {
        yeetService.postYeet(post, uid, username);
    }

    @PostMapping("/yeet/addLike")
    public void addLike(@RequestParam("yeetKey") String yeetKey, @RequestParam("uid") String uid)
            throws InterruptedException, FirebaseAuthException {
        yeetService.addLike(yeetKey, uid);
        yeetService.addUserToLikeList(yeetKey,uid);
        yeetService.managePostCounters(uid, yeetKey, "increase_likes");
    }

    @PostMapping("/yeet/removeLike")
    public void removeLike(@RequestParam("yeetKey") String yeetKey, @RequestParam("uid") String uid)
            throws InterruptedException, FirebaseAuthException {
        yeetService.removeLike(yeetKey, uid);
        yeetService.removeUserFromLikeList(yeetKey, uid);
        yeetService.managePostCounters(uid, yeetKey, "decrease_likes");
    }

    @PostMapping("/yeet/addDislike")
    public void addDislike(@RequestParam("yeetKey") String yeetKey, @RequestParam("uid") String uid)
            throws InterruptedException, FirebaseAuthException {
        yeetService.addDislike(yeetKey, uid);
        yeetService.addUserToDislikeList(yeetKey, uid);
        yeetService.managePostCounters(uid, yeetKey, "increase_dislikes");
    }

    @PostMapping("/yeet/removeDislike")
    public void removeDislike(@RequestParam("yeetKey") String yeetKey, @RequestParam("uid") String uid)
            throws InterruptedException, FirebaseAuthException {
        yeetService.removeDislike(yeetKey, uid);
        yeetService.removeUserFromDislikeList(yeetKey, uid);
        yeetService.managePostCounters(uid, yeetKey, "decrease_dislikes");
    }

    @GetMapping("/yeet/personalPosts")
    public List<Yeet> personalPosts(@RequestParam("uid") String uid)
            throws InterruptedException, FirebaseAuthException {
        return yeetService.getPersonalPosts(uid);
    }

    @GetMapping("/yeet/followingPosts")
    public List<Yeet> followingPosts(@RequestParam("uid") String uid)
            throws InterruptedException, FirebaseAuthException {
        return yeetService.getFollowingPosts(uid);
    }
}
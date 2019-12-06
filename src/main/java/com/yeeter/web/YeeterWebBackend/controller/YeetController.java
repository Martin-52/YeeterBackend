package com.yeeter.web.YeeterWebBackend.controller;

import com.yeeter.web.YeeterWebBackend.service.YeetService;

import java.util.List;

import com.google.firebase.auth.FirebaseAuthException;
import com.yeeter.web.YeeterWebBackend.model.Yeet;
import com.yeeter.web.YeeterWebBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class YeetController {
    @Autowired
    YeetService yeetService;

    @Autowired
    UserService userService;

    @GetMapping("/yeet/postYeet")
    public boolean postYeet(@PathVariable String post, @PathVariable String uid)
            throws InterruptedException, FirebaseAuthException {
        return yeetService.postYeet(post, uid);
    }

    @GetMapping("/yeet/likeDislike")
    public boolean likeDislike(@PathVariable String yeetKey, @PathVariable String uid)
            throws InterruptedException, FirebaseAuthException {
        return yeetService.likeDislike(yeetKey, uid);
    }

    @GetMapping("/yeet/getYeets")
    public List<Yeet> getYeets(@PathVariable String uid)
            throws InterruptedException, FirebaseAuthException {
        return yeetService.getYeets(uid);
    }
    
    @GetMapping("/yeet/updateYeet")
    public void updateYeet(
        @PathVariable String yeetKey,
        @PathVariable String postBody,
        @PathVariable String uid,
        @PathVariable int likes,
        @PathVariable int dislikes)
            throws InterruptedException, FirebaseAuthException {
        yeetService.updateYeet(yeetKey, postBody, uid, likes, dislikes);
    }

    @GetMapping("/yeet/addUserToLikeList")
    public void addUserToLikeList(@PathVariable String yeetKey, @PathVariable String uid)
            throws InterruptedException, FirebaseAuthException {
        yeetService.addUserToLikeList(yeetKey, uid);
    }

    @GetMapping("/yeet/removeUserFromLikeList")
    public void removeUserFromLikeList(@PathVariable String yeetKey, @PathVariable String uid)
            throws InterruptedException, FirebaseAuthException {
        yeetService.removeUserFromLikeList(yeetKey, uid);
    }

    @GetMapping("/yeet/addUserToDislikeList")
    public void addUserToDislikeList(@PathVariable String yeetKey, @PathVariable String uid)
            throws InterruptedException, FirebaseAuthException {
        yeetService.addUserToDislikeList(yeetKey, uid);
    }

    @GetMapping("/yeet/removeUserFromDislikeList")
    public void removeUserFromDislikeList(@PathVariable String yeetKey, @PathVariable String uid)
            throws InterruptedException, FirebaseAuthException {
        yeetService.removeUserFromDislikeList(yeetKey, uid);
    }

    @GetMapping("/yeet/addLike")
    public void addLike(@PathVariable String yeetKey, @PathVariable String uid)
            throws InterruptedException, FirebaseAuthException {
        yeetService.addLike(yeetKey, uid);
    }

    @GetMapping("/yeet/removeLike")
    public void removeLike(@PathVariable String yeetKey, @PathVariable String uid)
            throws InterruptedException, FirebaseAuthException {
        yeetService.removeLike(yeetKey, uid);
    }

    @GetMapping("/yeet/addDislike")
    public void addDislike(@PathVariable String yeetKey, @PathVariable String uid)
            throws InterruptedException, FirebaseAuthException {
        yeetService.addDislike(yeetKey, uid);
    }

    @GetMapping("/yeet/removeDislike")
    public void removeDislike(@PathVariable String yeetKey, @PathVariable String uid)
            throws InterruptedException, FirebaseAuthException {
        yeetService.removeDislike(yeetKey, uid);
    }

}
package com.yeeter.web.YeeterWebBackend.model;

public class Yeet {

    String yeet;
    String key;
    String username;
    String userId;
    Long likes;
    Long dislikes;
    boolean userDisliked;
    boolean userLiked;

    public Yeet(String yeet, String key, String username, String userId, Long likes, Long dislikes){
        this.yeet = yeet;
        this.key = key;
        this.username = username;
        this.userId = userId;
        this.likes = likes;
        this.dislikes = dislikes;
        this.userDisliked = false;
        this.userLiked = false;
    }

    public void setYeet(String yeet) {
        this.yeet = yeet;
    }

    public String getyeet() {
        return this.yeet;
    }

    public void setkey(String key) {
        this.key = key;    
    }

    public String getKey() {
        return this.key;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return this.userId;
    }    

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Long getLikes() {
        return this.likes;
    }

    public void setDislikes(Long dislikes) {
        this.dislikes = dislikes;    
    }

    public Long getDislikes() {
        return this.dislikes;
    }

    public void setUserDisliked(boolean userDisliked) {
        this.userDisliked = userDisliked;
    }

    public boolean getUserDisliked() {
        return this.userDisliked;
    }
   
    public void setUserLiked(boolean userLiked) {
        this.userLiked = userLiked;
    }

    public boolean getUserLiked() {
        return this.userLiked;
    }    
}
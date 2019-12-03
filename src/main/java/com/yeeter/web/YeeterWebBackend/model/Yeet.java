package com.yeeter.web.YeeterWebBackend.model;

import java.util.ArrayList;
import java.util.List;

public class Yeet {

    private String ref;
    private String id;
    private String post;
    private String key;

    private List<String> usersLikedList;
    private List<String> usersDislikedList;

    private int amountLikes;
    private int amountDislikes;

    public Yeet(String post,String key){
        this.ref = null;
        this.post = post;
        this.key = key;
        this.id = key;
        this.amountLikes = 0;
        this.amountDislikes = 0;
        this.usersLikedList = new ArrayList<String>();
        this.usersDislikedList = new ArrayList<String>();
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getRef() {
        return this.ref;
    }

    public void setId(String id) {
        this.id = id;    
    }

    public String getId() {
        return this.id;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPost() {
        return this.post;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

    public void setUsersLikedList(List<String> usersLikedList) {
        this.usersLikedList = usersLikedList;
    }

    public List<String> getUsersLikedList() {
        return this.usersLikedList;
    }

    public void setUsersDislikedList(List<String> usersDislikedList) {
        this.usersDislikedList = usersDislikedList;
    }

    public List<String> getUsersDislikedList() {
        return this.usersDislikedList;
    }

    public void setAmountLikes(int amountLikes) {
        this.amountLikes = amountLikes;
    }

    public int getAmountLikes() {
        return this.amountLikes;
    }

    public void setAmountDislikes(int amountDislikes) {
        this.amountDislikes = amountDislikes;    
    }

    public int getAmountDislikes() {
        return this.amountDislikes;
    }
}
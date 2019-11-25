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

    private Integer amountLikes;
    private Integer amountDislikes;

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
}
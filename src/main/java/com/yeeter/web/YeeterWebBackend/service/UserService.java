package com.yeeter.web.YeeterWebBackend.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.UpdateRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/*
    User Service class that we will use
    to make changes to the user.
    Whatever that may be.....
*/
public class UserService {
    FirebaseDatabase db = FirebaseDatabase.getInstance();

    public boolean uploadUser(String displayName, String uid) throws FirebaseAuthException {

        UpdateRequest req = new UpdateRequest(uid)
            .setDisplayName(displayName);
        
        FirebaseAuth.getInstance().updateUser(req);
        return true;
    }

    public boolean usernameExists(String username) {
        return false;
    }

    // Username is person to follow/unfollow.
    // Uid is current user.
    public boolean followUser(String username, String uid){
        return true;
    }  

    public boolean unfollowUser(String username, String uid){
        return true;
    }

    public boolean isFollowingUser(String username, String uid){
        return true;
    }


}
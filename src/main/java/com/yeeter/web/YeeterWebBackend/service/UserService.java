package com.yeeter.web.YeeterWebBackend.service;

import java.util.concurrent.CountDownLatch;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord.UpdateRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.opencensus.stats.Aggregation.Count;

/*
    User Service class that we will use
    to make changes to the user.
    Whatever that may be.....
*/
@Service
public class UserService {
    @Autowired
    FirebaseDatabase db;

    @Autowired
    FirebaseAuth auth;

    private boolean exists;
    private String userId;

    public boolean uploadUser(String displayName, String uid) throws FirebaseAuthException {

        UpdateRequest req = new UpdateRequest(uid).setDisplayName(displayName);

        auth.updateUser(req);
        return true;
    }

    public boolean usernameExists(String username) throws InterruptedException {
        DatabaseReference ref = db.getReference("/usernamesRef").child(username);
        CountDownLatch done = new CountDownLatch(1);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                exists = snapshot.exists();
                done.countDown();
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
        done.await();
        return exists;
    }

    // Username is person to follow/unfollow.
    // Uid is current user.
    public void followUser(String usernameToFollow, String currentUserId) throws InterruptedException {
        String usernameToId = getUserId(usernameToFollow);
        addToCheckFollowing(currentUserId, usernameToFollow, usernameToId);
        addToGetFollowing(currentUserId, usernameToFollow, usernameToId);
        addToUsersFollowers(usernameToId, currentUserId);
    }  

    public boolean unfollowUser(String username, String uid){
        return true;
    }

    public boolean isFollowingUser(String username, String uid){
        return true;
    }

    private String getUserId(String username) throws InterruptedException {
        DatabaseReference ref = db.getReference("/usernamesRef").child(username);
        CountDownLatch done = new CountDownLatch(1);
        userId = "";
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                userId = (String) snapshot.getValue();
                done.countDown();
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        done.await();
        return userId;
    }

    private void addToCheckFollowing(String currentUserId, String usernameToFollow, String userToFollowId){
        DatabaseReference ref = db
            .getReference("/check_following")
            .child(currentUserId)
            .child(usernameToFollow);
        ref.setValueAsync(userToFollowId);
    }
    private void addToGetFollowing(String currentUserId, String usernameToFollow, String userToFollowId){
        DatabaseReference ref = db
            .getReference("/get_following")
            .child(currentUserId)
            .child(userToFollowId);
        ref.setValueAsync(usernameToFollow);
    }
    private void addToUsersFollowers(String usernameToId, String currentUserId){
        DatabaseReference ref = db
            .getReference("/users_followers")
            .child(usernameToId)
            .child(currentUserId);
        ref.setValueAsync("User");
    }


}
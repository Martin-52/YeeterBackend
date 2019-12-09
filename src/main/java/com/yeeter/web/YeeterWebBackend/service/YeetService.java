package com.yeeter.web.YeeterWebBackend.service;

import com.yeeter.web.YeeterWebBackend.model.Yeet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yeeter.web.YeeterWebBackend.model.Yeet;

/*
    Yeet service class that will update a Yeets 
    numLikes, numDislikes, usersLikedList, usersDislikedList
    and any other operations that we may possible need to do.
*/
@Service
public class YeetService {
    @Autowired
    FirebaseDatabase db;

    private List<Yeet> personalYeets = new ArrayList<>();
    private List<Yeet> followingYeets = new ArrayList<>();
    private List<Yeet> tempYeets = new ArrayList<>();

    // Post is body of Yeet.
    // Uid is user that is posting Yeet.
    public void postYeet(String yeet, String currentUserId, String username) {
        DatabaseReference ref = db.getReference("/posts_by_user").child(currentUserId).push();

        ref.child("/likes").setValueAsync(0);
        ref.child("/dislikes").setValueAsync(0);
        ref.child("/userId").setValueAsync(currentUserId);
        ref.child("/username").setValueAsync(username);
        ref.child("/yeet").setValueAsync(yeet);
    }

    public boolean likeDislike(String yeetKey, String uid) {
        return true;
    }

    public List<Yeet> getPersonalPosts(String currentUserId) throws InterruptedException {
        personalYeets.clear();

        DatabaseReference ref = db.getReference("posts_by_user").child(currentUserId);
        CountDownLatch done = new CountDownLatch(1);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println(snapshot);
                for (DataSnapshot child : snapshot.getChildren()) {
                    personalYeets.add(new Yeet((String) child.child("/yeet").getValue(), (String) child.getKey(),
                            (String) child.child("/username").getValue(), (String) child.child("/userId").getValue(),
                            (Long) child.child("/likes").getValue(), (Long) child.child("/dislikes").getValue()));
                }
                done.countDown();
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        done.await();
        return personalYeets;
    }

    public List<Yeet> getFollowingPosts(String currentUserId) throws InterruptedException {
        followingYeets.clear();

        DatabaseReference ref = db.getReference("/check_following").child(currentUserId);
        CountDownLatch done = new CountDownLatch(1);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println(snapshot);
                for (DataSnapshot child : snapshot.getChildren()) {
                    try {
                        getFollowingPostsHelper((String) child.getValue());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                done.countDown();
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        done.await();
        return followingYeets;
    }

    // public void managePostCounters(String yeetKey, String currentUserId, boolean like) {
    //     DatabaseReference
    // }
    
    // Test starting from here. 
    public void addUserToLikeList(String yeetKey, String currentUserId) {
        DatabaseReference ref = db
            .getReference("/posts_xlist")
            .child(yeetKey)
            .child("/users_liked")
            .child(currentUserId);
        ref.setValueAsync("Dummy Value");
    }
    public void removeUserFromLikeList(String yeetKey, String currentUserId) {
        DatabaseReference ref = db
            .getReference("/posts_xlist")
            .child(yeetKey)
            .child("/users_liked")
            .child(currentUserId);

        ref.removeValueAsync();
    }
    public void addUserToDislikeList(String yeetKey, String currentUserId) {
        DatabaseReference ref = db
            .getReference("/posts_xlist")
            .child(yeetKey)
            .child("/users_disliked")
            .child(currentUserId);
        ref.setValueAsync("Dummy Value");
    }
    public void removeUserFromDislikeList(String yeetKey, String currentUserId) {
        DatabaseReference ref = db
            .getReference("/posts_xlist")
            .child(yeetKey)
            .child("/users_disliked")
            .child(currentUserId);

        ref.removeValueAsync();        
    }
    public void addLike(String yeetKey, String currentUserId) {
        DatabaseReference ref = db
            .getReference("/users_xlist")
            .child(currentUserId)
            .child("/user_likes")
            .child(yeetKey);
        ref.setValueAsync("Dummy Value");
    }
    public void removeLike(String yeetKey, String currentUserId) {
        DatabaseReference ref = db
            .getReference("/users_xlist")
            .child(currentUserId)
            .child("/user_likes")
            .child(yeetKey);
        ref.removeValueAsync();
    }
    public void addDislike(String yeetKey, String currentUserId) {
        DatabaseReference ref = db
            .getReference("/users_xlist")
            .child(currentUserId)
            .child("/user_dislikes")
            .child(yeetKey);
        ref.setValueAsync("Dummy Value");      
    }
    public void removeDislike(String yeetKey, String currentUserId) {
        DatabaseReference ref = db
            .getReference("/users_xlist")
            .child(currentUserId)
            .child("/user_dislikes")
            .child(yeetKey);
        ref.removeValueAsync();        
    }

    private void getFollowingPostsHelper(String userId) throws InterruptedException {
        DatabaseReference ref = db.getReference("posts_by_user").child(userId);
        CountDownLatch done = new CountDownLatch(1);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println(snapshot);
                for(DataSnapshot child: snapshot.getChildren()){
                    tempYeets.add(new Yeet(
                        (String) child.child("/yeet").getValue(),
                        (String) child.getKey(),
                        (String) child.child("/username").getValue(), 
                        (String) child.child("/userId").getValue(), 
                        (Long) child.child("/likes").getValue(), 
                        (Long) child.child("/dislikes").getValue()
                    ));
                }
                done.countDown();
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        done.await();
    }
}
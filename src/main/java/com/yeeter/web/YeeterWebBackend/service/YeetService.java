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
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.Transaction.Result;

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
    private List<String> followingUsers = new ArrayList<>();

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


    public List<Yeet> getPersonalPosts(String currentUserId) throws InterruptedException {
        personalYeets.clear();

        DatabaseReference ref = db.getReference("/posts_by_user").child(currentUserId);
        CountDownLatch done = new CountDownLatch(1);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    personalYeets.add(new Yeet(
                        (String) child.child("/yeet").getValue(),
                        (String) child.getKey(),
                        (String) child.child("/username").getValue(),
                        (String) child.child("/userId").getValue(),
                        (Long) child.child("/likes").getValue(),
                        (Long) child.child("/dislikes").getValue()));
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

    public void managePostCounters(final String userId, final String postKey, final String command){
        //System.out.println("path given: /posts_by_user/" + userId + "/" + postKey + "\n With command: " + command);

        final DatabaseReference posts_by_user = db
            .getReference("/posts_by_user")
            .child(userId)
            .child(postKey);
            //.child("/likes");
        posts_by_user.runTransaction(new Transaction.Handler(){
            @Override
            public void onComplete(DatabaseError error, boolean committed, DataSnapshot currentData) {
                System.out.println("ON COMPLETE");
                // TODO Auto-generated method stub
                // Int likeValue = currentData.child("/likes").getValue();
                //System.out.println("Data: "+ currentData.getValue().toString());
                if (committed) {
                    System.out.println("manage post counter commited");
                }
            }
            @Override
            public Result doTransaction(MutableData currentData) {
                System.out.println("DO TRANSACTION NOGGA");

                Long likeValue = (Long)currentData.child("/likes").getValue();
                Long dislikeValue = (Long)currentData.child("/dislikes").getValue();

                System.out.println("likes: "+ likeValue);
                System.out.println("dislikes: "+ dislikeValue);

                if(likeValue!=null && dislikeValue!=null){
                    if(command.equals("increase_likes")){
                        System.out.println("INCREASING NOGGA");
                        currentData.child("/likes").setValue(likeValue+1);
                    } else if (command.equals("decrease_likes")){
                        currentData.child("/likes").setValue(likeValue-1);
                    } else if (command.equals("increase_dislikes")){
                        currentData.child("/dislikes").setValue(dislikeValue+1);
                    } else if (command.equals("decrease_dislikes")){
                        currentData.child("/dislikes").setValue(dislikeValue-1);
                    }
                }

                return Transaction.success(currentData);
            }
        });
    }

    public List<Yeet> getFollowingPosts(String currentId) throws InterruptedException {
        return getFollowingPostsHelper(getFollowingUsers(currentId));
    }

    private List<String> getFollowingUsers(String currentUserId) throws InterruptedException {
        followingUsers.clear();

        DatabaseReference ref = db.getReference("/check_following").child(currentUserId);
        CountDownLatch done = new CountDownLatch(1);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    followingUsers.add((String) child.getValue());
                }
                done.countDown();
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        done.await();
        return followingUsers;
    }

    

    // public void managePostCounters(String yeetKey, String currentUserId, boolean like) {
    //     DatabaseReference
    // }
    
    // Test starting from here. 
    public void addUserToLikeList( final String postId, final String userId) throws InterruptedException {
        DatabaseReference post_liked_list = db
            .getReference("/posts_xlists")
            .child(postId)
            .child("/users_liked")
            .child(userId);
            
        post_liked_list.setValueAsync("dummyvalue");
    }
    public void removeUserFromLikeList(final String postId, final String userId) {
        DatabaseReference post_liked_list = db.getReference("/posts_xlists")
            .child(postId)
            .child("/users_liked")
            .child(userId);

        
        post_liked_list.removeValueAsync();
    }
    
    public void addUserToDislikeList(final String postId,final String userId) throws InterruptedException {
        DatabaseReference post_disliked_list = db
            .getReference("/posts_xlists")
            .child("/" + postId)
            .child("/users_disliked")
            .child(userId);
    
        post_disliked_list.setValueAsync("dummyvalue");
    }
    
    public void removeUserFromDislikeList(final String postId, final String userId) {
        final DatabaseReference post_disliked_list = db
            .getReference("/posts_xlists")
            .child("/" + postId)
            .child("/users_disliked")
            .child(userId);

        post_disliked_list.removeValueAsync();
    }

    public void addLike(String yeetKey, String currentUserId) {
        DatabaseReference ref = db
            .getReference("/users_xlists")
            .child(currentUserId)
            .child("/user_likes")
            .child(yeetKey);
        ref.setValueAsync("Dummy Value");
    }
    public void removeLike(String yeetKey, String currentUserId) {
        DatabaseReference ref = db
            .getReference("/users_xlists")
            .child(currentUserId)
            .child("/user_likes")
            .child(yeetKey);
        ref.removeValueAsync();
    }
    public void addDislike(String yeetKey, String currentUserId) {
        DatabaseReference ref = db
            .getReference("/users_xlists")
            .child(currentUserId)
            .child("/user_dislikes")
            .child(yeetKey);
        ref.setValueAsync("Dummy Value");
    }
    public void removeDislike(String yeetKey, String currentUserId) {
        DatabaseReference ref = db
            .getReference("/users_xlists")
            .child(currentUserId)
            .child("/user_dislikes")
            .child(yeetKey);
        ref.removeValueAsync();        
    }

    private List<Yeet> getFollowingPostsHelper(List<String> followingList) throws InterruptedException {
        followingYeets.clear();

        for (String user : followingList) {
            DatabaseReference ref = db.getReference("/posts_by_user").child(user);
            CountDownLatch temp = new CountDownLatch(1);
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
    
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    for(DataSnapshot child: snapshot.getChildren()){
                        followingYeets.add(new Yeet(
                            (String) child.child("/yeet").getValue(),
                            (String) child.getKey(),
                            (String) child.child("/username").getValue(), 
                            (String) child.child("/userId").getValue(), 
                            (Long) child.child("/likes").getValue(), 
                            (Long) child.child("/dislikes").getValue()
                        ));
                    }
                    temp.countDown();
                }
                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
            temp.await();
        }
    return followingYeets;
    }
}
package com.yeeter.web.YeeterWebBackend.service;

import com.yeeter.web.YeeterWebBackend.model.Yeet;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

// import com.google.firebase.database.DataSnapshot;
// import com.google.firebase.database.DatabaseError;
// import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
// import com.google.firebase.database.ValueEventListener;
// import com.yeeter.web.YeeterWebBackend.model.Yeet;

/*
    Yeet service class that will update a Yeets 
    numLikes, numDislikes, usersLikedList, usersDislikedList
    and any other operations that we may possible need to do.
*/
public class YeetService {
    @Autowired
    FirebaseDatabase db;
    
    // Post is body of Yeet.
    // Uid is user that is posting Yeet.
    public boolean postYeet(String post, String uid) {
        return true;
    }

    /*
        Check how iOS is doing this. Consult with Antonio.
    */
    public boolean likeDislike(String yeetKey, String uid) {
        return true;
    }

    public List<Yeet> getYeets(String uid) {
        return new ArrayList<Yeet>();
    }

    public void updateYeet(
    String yeetKey,
    String postBody,
    String uid, 
    int likes, 
    int dislikes) {
        
    }

    public void addUserToLikeList(String yeetKey, String uid) {}
    public void removeUserFromLikeList(String yeetKey, String uid) {}
    public void addUserToDislikeList(String yeetKey, String uid) {}
    public void removeUserFromDislikeList(String yeetKey, String uid) {}
    public void addLike(String yeetKey, String uid) {}
    public void removeLike(String yeetKey, String uid) {}
    public void addDislike(String yeetKey, String uid) {}
    public void removeDislike(String yeetKey, String uid) {}

    // Figure out if these are getting the number of likes/dislikes
    // of a Yeet, or are getting the like/dislike list of User.
    // ******If it is the latter, move this into the UserService class.*******
    public void getLikes() {}
    public void getDislikes() {}
}

    /*
        FOR REFERENCE... MAKE SURE TO DELETE
        THIS ONCE WE HAVE AN IDEA OF HOW TO IMPLEMENT.
    */

    // public void test() {
    // // Test method to understand how 
    // // to interact with the FireBase database.
    // // Any questions ask @Antonio

    //     DatabaseReference dref = db.getReference("/usernamesRef");


    //     // Attach a listener to read the data at our posts reference

    //     dref.addValueEventListener(new ValueEventListener() {
    //         @Override
    //         public void onDataChange(DataSnapshot dataSnapshot) {
    //         System.out.println(dataSnapshot.child("mlandin").getValue());
    //         }
        
    //         @Override
    //         public void onCancelled(DatabaseError databaseError) {
    //         System.out.println("The read failed: " + databaseError.getCode());
    //         }
    //     });
    // }
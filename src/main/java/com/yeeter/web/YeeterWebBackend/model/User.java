package com.yeeter.web.YeeterWebBackend.model;

public class User {
   private String username;
   private String userId;
   private Integer likesAmount;
   private Integer dislikesAmount;
   private Long followersAmount;

    public User(String username, String userId, Integer likesAmount, Integer dislikesAmount, Long followersAmount) {
      this.username=username;
      this.userId=userId;
      this.likesAmount=likesAmount;
      this.dislikesAmount=dislikesAmount;
      this.followersAmount=followersAmount;
    }

    public String getUsername() {
       return this.username;
    }

    public void setUsername(String username) {
       this.username = username;
    }

    public Integer getLikesAmount() {
      return this.likesAmount;
   }

   public void setLikesAmount(Integer amount) {
      this.likesAmount = amount;
   }

   public Integer getDislikesAmount() {
      return this.dislikesAmount;
   }

   public void setDislikesAmount(Integer amount) {
      this.dislikesAmount = amount;
   }

   public Long getFollowersAmount() {
      return this.followersAmount;
   }

   public void setFollowersAmount(long amount) {
      this.followersAmount = amount;
   }

}

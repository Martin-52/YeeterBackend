package com.yeeter.web.YeeterWebBackend.model;

public class User {
    private String username;
    private String password;
    private String email;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return this.email;
     }   

    public String getUsername() {
       return this.username;
    }

    public String getPassword() {
       return this.password;
    }

    public void setEmail(String email) {
        this.email = email;
     }

    public void setUsername(String username) {
       this.username = username;
    }
    public void setPassword(String password) {
       this.password = password;
    }

}

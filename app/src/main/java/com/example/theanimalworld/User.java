package com.example.theanimalworld;

public class User {
    String username,password;
    int age;
    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }
    public User(String username, String password, int age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }
}

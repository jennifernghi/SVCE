package com.example.android.svce.model.POJO;

/**
 * Created by jennifernghinguyen on 2/27/17.
 */

public class User {
    private String username;
    private String email;
    private String phoneNumber;
    private String password;

    public User(String username,
                String email,
                String phoneNumber,
                String password){
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public String getUsername(){
        return this.username;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public int getPasswordLength(){
        return this.password.length();
    }

    public String getPassword(){
        return this.password;
    }
}

package com.example.android.svce.model.POJO;

import com.google.android.gms.common.api.GoogleApiClient;

import java.io.Serializable;

/**
 * Created by jennifernghinguyen on 2/27/17.
 */

public class User implements Serializable {
    //private String username;
    private String email;
    private String userThumbnail;


    public User(//String username,
                String email,
                String password
               ){
       // this.username = username;
        this.email = email;
        this.userThumbnail = password;

    }

   /* public String getUsername(){
        return this.username;
    }*/

    public String getEmail(){
        return this.email;
    }

    public String getUserThumbnail(){
        return this.userThumbnail;
    }


}

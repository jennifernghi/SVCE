package com.example.android.svce.model.viewModel;

import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.svce.databinding.ActivityLoginBinding;
import com.example.android.svce.model.POJO.User;

/**
 * Created by jennifernghinguyen on 4/4/17.
 */

public class LoginViewModel {

    private User user;
    private Context context;
    private ActivityLoginBinding activityLoginBinding;


    public LoginViewModel(Context context, ActivityLoginBinding activityLoginBinding){
        this.context = context;
        this.activityLoginBinding = activityLoginBinding;
    }

    public void setUser(User user){
        this.user = user;
    }

    public User getUser(){ return this.user;}

    public Button getGoogleButton(){
        return activityLoginBinding.googleButton;
    }


}

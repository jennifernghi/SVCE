package com.example.android.svce.model.viewModel;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.svce.activities.MyInfoActivity;
import com.example.android.svce.activities.UserActivity;
import com.example.android.svce.databinding.ActivityLoginBinding;
import com.example.android.svce.model.POJO.User;
import com.google.android.gms.common.api.GoogleApiClient;

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
    public LinearLayout getEmptyView(){
        return activityLoginBinding.emptyview;
    }

    public TextView getEmptyViewMessage(){
        return activityLoginBinding.emptyviewMessage;
    }

}

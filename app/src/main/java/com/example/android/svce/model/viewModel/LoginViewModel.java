package com.example.android.svce.model.viewModel;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
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

    public EditText getUsernameEditText(){
        return activityLoginBinding.usernameEdittext;
    }

    public EditText getPasswordEditText(){
        return activityLoginBinding.passwordEdittext;
    }

    public Button getLoginButton(){
        return activityLoginBinding.loginButton;
    }
    public Button getSignupButton(){
        return activityLoginBinding.signupButton;
    }

    public LinearLayout getEmptyView(){
        return activityLoginBinding.emptyview;
    }

    public TextView getEmptyViewMessage(){
        return activityLoginBinding.emptyviewMessage;
    }
}

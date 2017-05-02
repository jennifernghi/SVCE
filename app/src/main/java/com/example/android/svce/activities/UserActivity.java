package com.example.android.svce.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.android.svce.R;
import com.example.android.svce.databinding.ActivityUserBinding;
import com.example.android.svce.model.POJO.User;
import com.example.android.svce.model.viewModel.UserActivityViewModel;
import com.example.android.svce.utils.Constant;

public class UserActivity extends AppCompatActivity {


    private ActivityUserBinding xmlBinding;
    private UserActivityViewModel viewModel;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        xmlBinding = DataBindingUtil.setContentView(this, R.layout.activity_user);
        getUserInfo();

    }

    public static void startIntent(Context context, User user) {
        Intent intent = new Intent(context, UserActivity.class);
        intent.putExtra(Constant.USER_INFO, user);
        context.startActivity(intent);
    }

    private void getUserInfo() {

         user = (User) getIntent().getSerializableExtra(Constant.USER_INFO);
         viewModel = new UserActivityViewModel(this, user);
        xmlBinding.setUserActivityViewModel(viewModel);

    }




}

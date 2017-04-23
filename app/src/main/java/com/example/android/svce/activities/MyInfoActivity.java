package com.example.android.svce.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import com.example.android.svce.R;
import com.example.android.svce.databinding.ActivityMyInfoBinding;
import com.example.android.svce.model.POJO.User;
import com.example.android.svce.model.viewModel.MyInfoModel;
import com.example.android.svce.utils.Constant;
import com.google.android.gms.common.api.GoogleApiClient;


import static com.example.android.svce.utils.Constant.USER_INFO;

public class MyInfoActivity extends AppCompatActivity {

    private ActivityMyInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().setTitle(getString(R.string.my_info));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_info);
        getUserInfo();
        showHomeArrow();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showHomeArrow() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static void startIntent(Context context, User user) {
        Intent intent = new Intent(context, MyInfoActivity.class);
        intent.putExtra(Constant.USER_INFO, user);
        context.startActivity(intent);
    }

    private void getUserInfo() {
        User user = (User) getIntent().getSerializableExtra(Constant.USER_INFO);
        MyInfoModel myInfo = new MyInfoModel(this, user);
        binding.setMyInfoModel(myInfo);

    }
}

package com.example.android.svce.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.svce.R;

public class UserActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        TextView userInfo = (TextView) findViewById(R.id.my_info);
        userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this, MyInfoActivity.class));
            }
        });

        TextView myIdeas = (TextView) findViewById(R.id.my_ideas);
        myIdeas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this, MyIdeasActivity.class));
            }
        });

        TextView mySavedIdeas = (TextView) findViewById(R.id.my_saved_ideas);
        mySavedIdeas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this, MySavedIdeasActivity.class));
            }
        });

        ImageView home = (ImageView) findViewById(R.id.list_button);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this, HomeActivity.class));
            }
        });
    }
}

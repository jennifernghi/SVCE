package com.example.android.svce.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import com.example.android.svce.R;
import com.example.android.svce.databinding.ActivityIdeaDetailsBinding;
import com.example.android.svce.model.POJO.Ideas;
import com.example.android.svce.model.POJO.User;
import com.example.android.svce.model.viewModel.HomeActivityViewModel;
import com.example.android.svce.model.viewModel.IdeaDetailViewModel;
import com.example.android.svce.utils.Constant;

public class IdeaDetailsActivity extends AppCompatActivity {

    ActivityIdeaDetailsBinding xmlBinding;
    IdeaDetailViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initializeBinding();

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

    public static void startIntent(Context context, User user, Ideas idea) {
        Intent intent = new Intent(context, IdeaDetailsActivity.class);
        intent.putExtra(Constant.USER_INFO, user);
        intent.putExtra(Constant.IDEA_INFO, idea);
        context.startActivity(intent);
    }

    private void initializeBinding() {
        xmlBinding = DataBindingUtil.setContentView(this, R.layout.activity_idea_details);
        User user = (User) getIntent().getSerializableExtra(Constant.USER_INFO);
        Ideas idea = (Ideas) getIntent().getSerializableExtra(Constant.IDEA_INFO);
        viewModel = new IdeaDetailViewModel(this, xmlBinding, user, idea);
        xmlBinding.setIdeaDetailViewModel(viewModel);
    }
}

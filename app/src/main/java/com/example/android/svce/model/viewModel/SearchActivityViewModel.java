package com.example.android.svce.model.viewModel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.android.svce.activities.AddNewIdea;
import com.example.android.svce.activities.HomeActivity;
import com.example.android.svce.activities.IdeaDetailsActivity;
import com.example.android.svce.activities.SearchActivity;
import com.example.android.svce.activities.UserActivity;
import com.example.android.svce.databinding.ActivityHomeBinding;
import com.example.android.svce.databinding.ActivitySearchBinding;
import com.example.android.svce.model.POJO.User;

/**
 * Created by jennifernghinguyen on 4/26/17.
 */

public class SearchActivityViewModel {
    private User user;
    private Context context;
    private ActivitySearchBinding binding;
    public SearchActivityViewModel(Context context, ActivitySearchBinding binding, User user){
        this.context=context;
        this.binding=binding;
        this.user = user;
    }

    public RecyclerView getRecyclerView(){
        return binding.categoryList;
    }

    public User getUser(){
        return this.user;
    }

    public Context getContext(){
        return this.context;
    }

    public void addIdea(View view){
        AddNewIdea.startIntent(getContext(), getUser());
    }

    public void searchCategory(View view){
        SearchActivity.startIntent(getContext(), getUser());
    }
    public void openUserActivity(View view){
        UserActivity.startIntent(getContext(), getUser());
    }
    public void openHomeActivity(View view){
        HomeActivity.startIntent(getContext(), getUser(), null, null);
    }
}

package com.example.android.svce.model.viewModel;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.svce.activities.AddNewIdea;

import com.example.android.svce.activities.Login;
import com.example.android.svce.activities.SearchActivity;
import com.example.android.svce.activities.UserActivity;
import com.example.android.svce.databinding.ActivityHomeBinding;
import com.example.android.svce.model.POJO.User;
import com.example.android.svce.utils.Utils;

/**
 * Created by jennifernghinguyen on 4/21/17.
 */

public class HomeActivityViewModel {

    private User user;
    private Context context;
    private ActivityHomeBinding binding;

    public HomeActivityViewModel(Context context, ActivityHomeBinding binding, User user){
        this.context=context;
        this.binding=binding;
        this.user = user;
    }


    public RecyclerView getRecyclerView(){
        return binding.ideasList;
    }

    public User getUser(){
        return this.user;
    }

    public Context getContext(){
        return this.context;
    }

    public FloatingActionButton getAddButton(){
        return binding.add;
    }

    public ImageView getListButton(){
        return binding.listButton;
    }

    public ImageView getSearchButton(){
        return binding.searchButton;
    }

    public ImageView getUserButton(){
        return binding.userButton;
    }

    public ImageView getLogoutButton(){
        return binding.logoutButton;
    }

    public ProgressBar getProgressBar(){
        return binding.loadingIndicator;
    }

    public TextView getEmptyTextView(){
        return binding.emptyViewMessage;
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

    public void logout(View view){
        Login.startIntent(getContext(), getUser());
    }
}

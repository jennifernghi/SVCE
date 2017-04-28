package com.example.android.svce.model.viewModel;

import android.content.Context;
import android.view.View;

import com.example.android.svce.activities.HomeActivity;
import com.example.android.svce.activities.IdeaDetailsActivity;
import com.example.android.svce.model.POJO.Category;
import com.example.android.svce.model.POJO.Comment;
import com.example.android.svce.model.POJO.User;
import com.example.android.svce.utils.Utils;

/**
 * Created by jennifernghinguyen on 4/26/17.
 */

public class CategoryItemViewModel {

    private Context context;
    private User user;
    private Category category;

    public CategoryItemViewModel(Context context, User user, Category category){
        this.category = category;
        this.user = user;
        this.context = context;
    }


    public User getUser(){
        return this.user;
    }


    public Context getContext(){
        return this.context;
    }

    public Category getCategory(){
        return this.category;
    }

    public String getCategoryName(){
        return this.category.getCategory();
    }
    public void onSelected(View view){
        HomeActivity.startIntent(view.getContext(), getUser(), getCategory(), null);

    }
}

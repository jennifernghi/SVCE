package com.example.android.svce.model.viewModel;

import android.content.Context;

import com.example.android.svce.model.POJO.Ideas;
import com.example.android.svce.model.POJO.User;

import static com.example.android.svce.R.drawable.user;

/**
 * Created by jennifernghinguyen on 2/27/17.
 */

public class IdeasItemViewModel {

    private Ideas ideas;
    private Context context;


    public IdeasItemViewModel(Context context, Ideas ideas){
        this.context = context;
        this.ideas = ideas;

    }

    public Context getContext(){
        return this.context;
    }
    public String getTitle(){
        return this.ideas.getTitle();
    }

    public String getAuthor(){
        return "@ "+ this.ideas.getAuthor();
    }

    public String getContent(){
        return this.ideas.getContent();
    }

    public String getDate(){
        return this.ideas.getDate();
    }

    public String getVote(){
        return String.valueOf(this.ideas.getLikes());
    }

    public String getCategory(){
        return this.ideas.getCategory();
    }


}

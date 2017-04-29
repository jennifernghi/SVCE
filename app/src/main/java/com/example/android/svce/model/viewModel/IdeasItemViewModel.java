package com.example.android.svce.model.viewModel;

import android.content.Context;
import android.view.View;

import com.example.android.svce.activities.IdeaDetailsActivity;
import com.example.android.svce.model.POJO.Ideas;
import com.example.android.svce.model.POJO.User;
import com.example.android.svce.utils.Utils;

/**
 * Created by jennifernghinguyen on 2/27/17.
 */

public class IdeasItemViewModel{

    private Ideas ideas;
    private Context context;
    private User user;

    public IdeasItemViewModel(Context context, Ideas ideas, User user){
        this.context = context;
        this.ideas = ideas;
        this.user = user;

    }

    public Context getContext(){
        return this.context;
    }
    public String getTitle(){
        return this.ideas.getTitle();
    }
    public User getUser(){
        return this.user;
    }
    public String getAuthor(){
        return "@" + Utils.trimEmailPart(this.ideas.getAuthor());
    }

    public Ideas getIdea(){return this.ideas;}

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
        return "#" + this.ideas.getCategory();
    }


    public void onSelected(View view){
        IdeaDetailsActivity.startIntent(view.getContext(), getUser(), getIdea());
    }

}

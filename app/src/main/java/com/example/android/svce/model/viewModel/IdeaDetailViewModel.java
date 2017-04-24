package com.example.android.svce.model.viewModel;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.android.svce.activities.IdeaDetailsActivity;
import com.example.android.svce.databinding.ActivityHomeBinding;
import com.example.android.svce.databinding.ActivityIdeaDetailsBinding;
import com.example.android.svce.model.POJO.Ideas;
import com.example.android.svce.model.POJO.User;
import com.example.android.svce.utils.StringUtils;

/**
 * Created by jennifernghinguyen on 4/23/17.
 */

public class IdeaDetailViewModel {

    private User user;
    private Context context;
    private ActivityIdeaDetailsBinding binding;
    private Ideas idea;

    public IdeaDetailViewModel(Context context, ActivityIdeaDetailsBinding binding, User user, Ideas idea){
        this.context=context;
        this.binding=binding;
        this.user = user;
        this.idea= idea;
    }


    /*public String getUserUsername() {
        return this.user.getUsername();
    }*/

    public String getUserEmail() {
        return StringUtils.trimEmailPart( this.user.getEmail());
    }

    public String getUserThumbnail() {
        return this.user.getUserThumbnail();
    }


    public User getUser(){
        return this.user;
    }

    public Ideas getIdea(){
        return this.idea;
    }

    public Context getContext(){
        return this.context;
    }

    public String getTitle(){
        return this.idea.getTitle();
    }

    public String getAuthor(){
        return "@ " + StringUtils.trimEmailPart(this.idea.getAuthor());
    }

    public String getContent(){
        return this.idea.getContent();
    }

    public String getDate(){
        return this.idea.getDate();
    }

    public String getVote(){
        return String.valueOf(this.idea.getLikes());
    }

    public String getCategory(){
        return this.idea.getCategory();
    }

    public RecyclerView getCommentList(){
        return this.binding.commentList;
    }


}

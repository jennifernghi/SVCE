package com.example.android.svce.model.viewModel;

import android.content.Context;

import com.example.android.svce.model.POJO.Comment;
import com.example.android.svce.model.POJO.Ideas;
import com.example.android.svce.model.POJO.User;
import com.example.android.svce.utils.StringUtils;

/**
 * Created by jennifernghinguyen on 4/23/17.
 */

public class CommentItemViewModel {
    private Context context;
    private User user;
    private Comment comment;

    public CommentItemViewModel(Context context, User user, Comment comment){
        this.comment = comment;
        this.user = user;
        this.context = context;
    }


    public User getUser(){
        return this.user;
    }


    public Context getContext(){
        return this.context;
    }

    public String getAuthor(){
        return "@ " + StringUtils.trimEmailPart(this.comment.getAuthor());
    }
    public String getComment(){
        return this.comment.getComment();
    }

}

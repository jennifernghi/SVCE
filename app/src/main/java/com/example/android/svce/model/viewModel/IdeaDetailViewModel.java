package com.example.android.svce.model.viewModel;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.android.svce.databinding.ActivityIdeaDetailsBinding;
import com.example.android.svce.model.POJO.Ideas;
import com.example.android.svce.model.POJO.User;
import com.example.android.svce.utils.Utils;

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
        return Utils.trimEmailPart( this.user.getEmail());
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
        return "@ " + Utils.trimEmailPart(this.idea.getAuthor());
    }

    public String getAuthorEmail(){
        return this.idea.getAuthor();
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

    public void sendEmail(View view){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {getAuthorEmail()});
        intent.putExtra(Intent.EXTRA_SUBJECT, getTitle());
        if(intent.resolveActivity(getContext().getPackageManager()) !=null){
            getContext().startActivity(intent);
        }
    }

}

package com.example.android.svce.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.android.svce.R;
import com.example.android.svce.databinding.CommentItemBinding;
import com.example.android.svce.databinding.IdeasItemBinding;
import com.example.android.svce.model.POJO.Comment;
import com.example.android.svce.model.POJO.Ideas;
import com.example.android.svce.model.POJO.User;

import java.util.ArrayList;

/**
 * Created by jennifernghinguyen on 2/3/17.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder> {
    private ArrayList<Comment> comments = new ArrayList<>();
    private User user;
    public CommentAdapter(ArrayList<Comment> data, User user) {
        super();
        comments = data;
        this.user = user;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

          CommentItemBinding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.comment_item, parent, false);

        return new CommentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, final int position) {

        holder.bindConnection(comments.get(position), user);

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public  void setLoadedIdeas(ArrayList<Comment> data){
        comments = data;
        notifyDataSetChanged();
    }


}

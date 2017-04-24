package com.example.android.svce.adapters;

import android.support.v7.widget.RecyclerView;

import com.example.android.svce.databinding.CommentItemBinding;
import com.example.android.svce.databinding.IdeasItemBinding;
import com.example.android.svce.model.POJO.Comment;
import com.example.android.svce.model.POJO.User;
import com.example.android.svce.model.viewModel.CommentItemViewModel;
import com.example.android.svce.model.viewModel.IdeasItemViewModel;

/**
 * Created by jennifernghinguyen on 2/4/17.
 */

public class CommentViewHolder extends RecyclerView.ViewHolder {

    private CommentItemBinding binding;

    public CommentViewHolder(CommentItemBinding _binding) {
        super(_binding.getRoot());
        binding = _binding;
    }

    public void bindConnection(Comment comment, User user) {
        //bind view model ProductItemViewModel with product_list_item.xml
        binding.setCommentItemViewModel(new CommentItemViewModel(itemView.getContext(), user, comment));
    }




}

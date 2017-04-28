package com.example.android.svce.adapters;

import android.support.v7.widget.RecyclerView;

import com.example.android.svce.databinding.CategoryItemBinding;
import com.example.android.svce.databinding.CommentItemBinding;
import com.example.android.svce.model.POJO.Category;
import com.example.android.svce.model.POJO.Comment;
import com.example.android.svce.model.POJO.User;
import com.example.android.svce.model.viewModel.CategoryItemViewModel;
import com.example.android.svce.model.viewModel.CommentItemViewModel;

/**
 * Created by jennifernghinguyen on 2/4/17.
 */

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    private CategoryItemBinding binding;

    public CategoryViewHolder(CategoryItemBinding _binding) {
        super(_binding.getRoot());
        binding = _binding;
    }

    public void bindConnection(Category category, User user) {
        //bind view model ProductItemViewModel with product_list_item.xml
        binding.setCategoryItemViewModel(new CategoryItemViewModel(itemView.getContext(), user, category));
    }




}

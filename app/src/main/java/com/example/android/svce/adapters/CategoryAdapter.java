package com.example.android.svce.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.android.svce.R;
import com.example.android.svce.databinding.CategoryItemBinding;
import com.example.android.svce.databinding.CommentItemBinding;
import com.example.android.svce.model.POJO.Category;
import com.example.android.svce.model.POJO.Comment;
import com.example.android.svce.model.POJO.User;

import java.util.ArrayList;

import static com.example.android.svce.R.string.comments;

/**
 * Created by jennifernghinguyen on 2/3/17.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {
    private ArrayList<Category> categories = new ArrayList<>();
    private User user;
    public CategoryAdapter(ArrayList<Category> data, User user) {
        super();
        categories = data;
        this.user = user;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

          CategoryItemBinding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.category_item, parent, false);

        return new CategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, final int position) {

        holder.bindConnection(categories.get(position), user);

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public  void setLoadedIdeas(ArrayList<Category> data){
        categories = data;
        notifyDataSetChanged();;
    }


}

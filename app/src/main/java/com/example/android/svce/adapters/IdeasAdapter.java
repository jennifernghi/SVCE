package com.example.android.svce.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.android.svce.R;
import com.example.android.svce.databinding.IdeasItemBinding;
import com.example.android.svce.model.POJO.Ideas;
import com.example.android.svce.model.POJO.User;

import java.util.ArrayList;

/**
 * Created by jennifernghinguyen on 2/3/17.
 */

public class IdeasAdapter extends RecyclerView.Adapter<IdeasViewHolder> {
    private ArrayList<Ideas> ideas = new ArrayList<>();
    private User user;
    public IdeasAdapter(ArrayList<Ideas> data, User user) {
        super();
        ideas = data;
        this.user = user;
    }

    @Override
    public IdeasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

          IdeasItemBinding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.ideas_item, parent, false);

        return new IdeasViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(IdeasViewHolder holder, final int position) {
        // bind each product with view model: ProductItemViewModel
        holder.bindConnection(ideas.get(position), user);

    }

    @Override
    public int getItemCount() {
        return ideas.size();
    }

    public  void setLoadedIdeas(ArrayList<Ideas> data){
        ideas = data;
        notifyDataSetChanged();;
    }


}

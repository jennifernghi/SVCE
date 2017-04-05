package com.example.android.svce.adapters;

import android.support.v7.widget.RecyclerView;
import com.example.android.svce.databinding.IdeasItemBinding;
import com.example.android.svce.model.POJO.Ideas;
import com.example.android.svce.model.viewModel.IdeasItemViewModel;

/**
 * Created by jennifernghinguyen on 2/4/17.
 */

public class IdeasViewHolder extends RecyclerView.ViewHolder {

    private IdeasItemBinding binding;

    public IdeasViewHolder(IdeasItemBinding _binding) {
        super(_binding.getRoot());
        binding = _binding;
    }

    public void bindConnection(Ideas ideas) {
        //bind view model ProductItemViewModel with product_list_item.xml
        binding.setIdeasItemViewModel(new IdeasItemViewModel(itemView.getContext(), ideas));
    }


}

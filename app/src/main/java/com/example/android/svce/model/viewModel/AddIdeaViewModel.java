package com.example.android.svce.model.viewModel;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.android.svce.activities.AddNewIdea;
import com.example.android.svce.databinding.ActivityAddNewIdeaBinding;
import com.example.android.svce.model.POJO.User;

/**
 * Created by jennifernghinguyen on 4/24/17.
 */

public class AddIdeaViewModel {

    private Context context;
    private User user;
    private ActivityAddNewIdeaBinding binding;

    public AddIdeaViewModel(Context context, ActivityAddNewIdeaBinding binding, User user){
        this.context = context;
        this.binding = binding;
        this.user = user;
    }

    public EditText getTitleEditText(){
        return binding.title;
    }

    public EditText getContentEditText(){
        return binding.content;
    }

    public Spinner getCategorySpinner(){
        return binding.category;
    }

    public FloatingActionButton getSaveButton(){
        return binding.save;
    }

    public TextView getCloseButton(){
        return binding.close;
    }

    public String getAuthor(){
        return this.user.getEmail();
    }


}

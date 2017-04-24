package com.example.android.svce.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.android.svce.R;
import com.example.android.svce.databinding.ActivityAddNewIdeaBinding;
import com.example.android.svce.model.POJO.Ideas;
import com.example.android.svce.model.POJO.User;
import com.example.android.svce.model.viewModel.AddIdeaViewModel;
import com.example.android.svce.model.viewModel.IdeaDetailViewModel;
import com.example.android.svce.utils.Constant;

public class AddNewIdea extends AppCompatActivity {

    private ActivityAddNewIdeaBinding xmlBinding;
    private AddIdeaViewModel viewModel;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        initializeBinding();
        initializeSpinner();
    }

    public static void startIntent(Context context, User user) {
        Intent intent = new Intent(context, AddNewIdea.class);
        intent.putExtra(Constant.USER_INFO, user);
        context.startActivity(intent);
    }

    private void initializeBinding() {
        xmlBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_idea);
        user = (User) getIntent().getSerializableExtra(Constant.USER_INFO);
        viewModel = new AddIdeaViewModel(this, xmlBinding, user);
        xmlBinding.setAddIdeaViewModel(viewModel);
    }

    private void initializeSpinner(){
        Spinner spinner = viewModel.getCategorySpinner();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }
}

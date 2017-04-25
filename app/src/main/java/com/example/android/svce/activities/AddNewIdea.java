package com.example.android.svce.activities;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.android.svce.R;
import com.example.android.svce.databinding.ActivityAddNewIdeaBinding;
import com.example.android.svce.model.POJO.Ideas;
import com.example.android.svce.model.POJO.User;
import com.example.android.svce.model.viewModel.AddIdeaViewModel;
import com.example.android.svce.model.viewModel.IdeaDetailViewModel;
import com.example.android.svce.networking.IdeasPost;
import com.example.android.svce.utils.Constant;
import com.example.android.svce.utils.Utils;

import java.util.ArrayList;

public class AddNewIdea extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private ActivityAddNewIdeaBinding xmlBinding;
    private AddIdeaViewModel viewModel;
    private User user;
    private String title;
    private String content;
    private String date;
    private String author;
    private String category;
    private int likes = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        initializeBinding();
        initializeSpinner();
        viewModel.getSaveButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPosting(Constant.LOADING_CONSTANT);
            }
        });
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

    private String gettitle(){
       return viewModel.getTitleEditText().getText().toString();
    }

    private String getContent(){
        return viewModel.getContentEditText().getText().toString();
    }

    private String getCategory(){
        return viewModel.getCategorySpinner().getSelectedItem().toString();
    }

    private String getDate(){
        return Utils.getTodayDate();
    }

    private String getAuthor(){
        return viewModel.getAuthor();
    }
    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new IdeasPost(getApplicationContext(), gettitle(), getContent(), getDate(), getCategory(), String.valueOf(likes), getAuthor());
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        Log.i("post", data);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        loader.reset();
    }

    public void startPosting(int loaderConstant) {
        if (checkNetWorkConnection()) {
            getLoaderManager().initLoader(loaderConstant, null, AddNewIdea.this);
        } else {
            //if no internet connection show empty view
            // viewModel.enableEmptyView(true, getString(R.string.no_network));
        }
    }

    private boolean checkNetWorkConnection() {

        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }
}

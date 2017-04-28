package com.example.android.svce.activities;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Window;

import com.example.android.svce.R;
import com.example.android.svce.adapters.CategoryAdapter;
import com.example.android.svce.adapters.CommentAdapter;
import com.example.android.svce.adapters.IdeasAdapter;
import com.example.android.svce.databinding.ActivityHomeBinding;
import com.example.android.svce.databinding.ActivitySearchBinding;
import com.example.android.svce.model.POJO.Category;
import com.example.android.svce.model.POJO.Comment;
import com.example.android.svce.model.POJO.Ideas;
import com.example.android.svce.model.POJO.User;
import com.example.android.svce.model.viewModel.HomeActivityViewModel;
import com.example.android.svce.model.viewModel.SearchActivityViewModel;
import com.example.android.svce.networking.IdeasLoader;
import com.example.android.svce.utils.Constant;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity  {

    private ActivitySearchBinding xmlBinding;
    private SearchActivityViewModel viewModel;
    private CategoryAdapter categoryAdapter;
    private ArrayList<Category> categories = new ArrayList<>();
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        getCaterory();
        initializeView();


    }

    public static void startIntent(Context context, User user) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(Constant.USER_INFO, user);
        context.startActivity(intent);
    }


    private void initializeBinding() {
        xmlBinding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        user = (User) getIntent().getSerializableExtra(Constant.USER_INFO);
        viewModel = new SearchActivityViewModel(this, xmlBinding, user);
        xmlBinding.setSearchActivityViewModel(viewModel);
    }

    private void initializeView() {
        initializeBinding();
        initializeRecyclerView(viewModel.getRecyclerView());
        Log.i("home", "initialize view");
    }

    private void initializeRecyclerView(RecyclerView recyclerView) {
        categoryAdapter = new CategoryAdapter(categories, user);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(categoryAdapter);
    }

    private void getCaterory(){
        String[] catarray = getResources().getStringArray(R.array.category);
        for(int i =0; i<catarray.length; i++){
            categories.add(new Category(catarray[i]));
        }
    }


}

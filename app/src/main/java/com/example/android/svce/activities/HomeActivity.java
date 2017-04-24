package com.example.android.svce.activities;
import android.app.LoaderManager;
import android.content.Loader;
import android.content.Context;
import android.content.Intent;
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
import com.example.android.svce.adapters.IdeasAdapter;
import com.example.android.svce.databinding.ActivityHomeBinding;
import com.example.android.svce.model.POJO.Ideas;
import com.example.android.svce.model.POJO.User;
import com.example.android.svce.model.viewModel.HomeActivityViewModel;
import com.example.android.svce.networking.IdeasLoader;
import com.example.android.svce.utils.Constant;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Ideas>> {

    private ActivityHomeBinding xmlBinding;
    private HomeActivityViewModel viewModel;
    private IdeasAdapter ideasAdapter;
    private ArrayList<Ideas> ideas = new ArrayList<>();
    private String sort = "newest";
    String startIndex = "";
    String endIndex = "";
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        initializeView();
        startLoading(Constant.LOADING_CONSTANT);

    }

    public static void startIntent(Context context, User user) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.putExtra(Constant.USER_INFO, user);
        context.startActivity(intent);
    }


    private void initializeBinding() {
        xmlBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        user = (User) getIntent().getSerializableExtra(Constant.USER_INFO);
        viewModel = new HomeActivityViewModel(this, xmlBinding, user);
        xmlBinding.setHomeActivityViewModel(viewModel);
    }

    private void initializeView() {
        initializeBinding();
        initializeRecyclerView(viewModel.getRecyclerView());
        Log.i("home", "initialize view");
    }

    private void initializeRecyclerView(RecyclerView recyclerView) {
        ideasAdapter = new IdeasAdapter(ideas, user);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(ideasAdapter);
    }

    @Override
    public Loader<ArrayList<Ideas>> onCreateLoader(int id, Bundle args) {
        Log.i("home", "on create loader");
        return new IdeasLoader(getApplicationContext(), Constant.HOST, sort, startIndex, endIndex);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Ideas>> loader, ArrayList<Ideas> data) {
        if(data.size()>1){
            ideas = data;
            ideasAdapter.setLoadedIdeas(ideas);
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Ideas>> loader) {
        loader.reset();
    }
    public void startLoading(int loaderConstant) {
        if (checkNetWorkConnection()) {
            Log.i("home", "startloading");
            getLoaderManager().initLoader(loaderConstant, null, HomeActivity.this);
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

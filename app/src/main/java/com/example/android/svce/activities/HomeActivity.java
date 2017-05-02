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
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.example.android.svce.R;
import com.example.android.svce.adapters.IdeasAdapter;
import com.example.android.svce.databinding.ActivityHomeBinding;
import com.example.android.svce.model.POJO.Category;
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
    private String category = "";
    private String author = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        initializeView();
        startLoading(Constant.LOADING_CONSTANT);

    }

    public static void startIntent(Context context, User user, Category category, String author) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.putExtra(Constant.USER_INFO, user);
        if(category!=null) {
            intent.putExtra(Constant.CATEGORY_INFO, category);
        }
        if(author!=null) {
            intent.putExtra(Constant.AUTHOR_INFO, author);
        }
        context.startActivity(intent);
    }


    private void initializeBinding() {
        xmlBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        user = (User) getIntent().getSerializableExtra(Constant.USER_INFO);
        if ((getIntent().getSerializableExtra(Constant.CATEGORY_INFO))!=null) {
            category = ((Category) getIntent().getSerializableExtra(Constant.CATEGORY_INFO)).getCategory();

         }
        if ((getIntent().getSerializableExtra(Constant.AUTHOR_INFO))!=null) {
            author = (String) getIntent().getSerializableExtra(Constant.AUTHOR_INFO);

        }
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
        return new IdeasLoader(getApplicationContext(), Constant.HOST, sort, category, author, startIndex, endIndex);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Ideas>> loader, ArrayList<Ideas> data) {
        if(data.size()>=1){
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sort_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.newest:
                sort = getResources().getString(R.string.newest);
                getLoaderManager().restartLoader(Constant.LOADING_CONSTANT, null, HomeActivity.this );
                return true;
            case R.id.oldest:
                sort = getResources().getString(R.string.oldest);
                getLoaderManager().restartLoader(Constant.LOADING_CONSTANT, null, HomeActivity.this );
                return true;
            case R.id.likes:
                sort = getResources().getString(R.string.likes);
                getLoaderManager().restartLoader(Constant.LOADING_CONSTANT, null, HomeActivity.this );
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {

    }
}

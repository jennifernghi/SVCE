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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;

import com.example.android.svce.R;
import com.example.android.svce.adapters.CommentAdapter;
import com.example.android.svce.adapters.IdeasAdapter;
import com.example.android.svce.databinding.ActivityIdeaDetailsBinding;
import com.example.android.svce.model.POJO.Comment;
import com.example.android.svce.model.POJO.Ideas;
import com.example.android.svce.model.POJO.User;
import com.example.android.svce.model.viewModel.HomeActivityViewModel;
import com.example.android.svce.model.viewModel.IdeaDetailViewModel;
import com.example.android.svce.networking.CommentLoader;
import com.example.android.svce.utils.Constant;

import java.util.ArrayList;

public class IdeaDetailsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Comment>> {

    private ActivityIdeaDetailsBinding xmlBinding;
    private IdeaDetailViewModel viewModel;
    private CommentAdapter commentAdapter;
    private ArrayList<Comment> comments = new ArrayList<>();
    private User user;
    private Ideas idea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initializeBinding();
        initializeCommentRecyclerView(viewModel.getCommentList());
        startCommentLoading(Constant.LOADING_CONSTANT);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static void startIntent(Context context, User user, Ideas idea) {
        Intent intent = new Intent(context, IdeaDetailsActivity.class);
        intent.putExtra(Constant.USER_INFO, user);
        intent.putExtra(Constant.IDEA_INFO, idea);
        context.startActivity(intent);
    }

    private void initializeBinding() {
        xmlBinding = DataBindingUtil.setContentView(this, R.layout.activity_idea_details);
        user = (User) getIntent().getSerializableExtra(Constant.USER_INFO);
        idea = (Ideas) getIntent().getSerializableExtra(Constant.IDEA_INFO);
        viewModel = new IdeaDetailViewModel(this, xmlBinding, user, idea);
        xmlBinding.setIdeaDetailViewModel(viewModel);
    }

    private void initializeCommentRecyclerView(RecyclerView recyclerView) {
        commentAdapter = new CommentAdapter(comments, user);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(commentAdapter);
    }

    @Override
    public Loader<ArrayList<Comment>> onCreateLoader(int id, Bundle args) {
        return new CommentLoader(getApplicationContext(), Constant.HOST, String.valueOf(idea.getIdeaId()));
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Comment>> loader, ArrayList<Comment> data) {
        if(data.size()>1){
            comments = data;
            commentAdapter.setLoadedIdeas(comments);
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Comment>> loader) {
        loader.reset();
    }

    public void startCommentLoading(int loaderConstant) {
        if (checkNetWorkConnection()) {
            getLoaderManager().initLoader(loaderConstant, null, IdeaDetailsActivity.this);
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

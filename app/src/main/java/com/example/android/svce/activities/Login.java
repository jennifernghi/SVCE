package com.example.android.svce.activities;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.android.svce.R;
import com.example.android.svce.databinding.ActivityLoginBinding;
import com.example.android.svce.model.POJO.User;
import com.example.android.svce.model.viewModel.LoginViewModel;
import com.example.android.svce.networking.UserLoader;
import com.example.android.svce.utils.Constant;

public class Login extends AppCompatActivity implements LoaderManager.LoaderCallbacks<User> {

    private ActivityLoginBinding activityLoginBinding; //bind to xml UI
    private LoginViewModel loginViewModel; //bind to view model

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        initializeBinding();
        loginViewModel.getLoginButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }
    /**
     * set up UI with databinding
     */
    private void initializeBinding(){
        //set up binding to UI
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        //hook UI to login view model class
        loginViewModel = new LoginViewModel(this, activityLoginBinding);
        activityLoginBinding.setLoginViewModel(loginViewModel);
    }

    /**
     * check internet connection
     *
     * @return true/false
     */
    private boolean checkNetWorkConnection() {

        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    private void login(){
        if (checkNetWorkConnection()) {
            loginViewModel.getEmptyView().setVisibility(View.VISIBLE);
            getLoaderManager().initLoader(Constant.LOADING_CONSTANT, null, Login.this);
        } else {
            //if no internet connection show empty view
            loginViewModel.getEmptyViewMessage().setText("no internet");
        }
    }

    @Override
    public Loader<User> onCreateLoader(int id, Bundle args) {
        return new UserLoader(getApplicationContext(), Constant.HOST, loginViewModel.getUsernameEditText().getText().toString().trim());
    }

    @Override
    public void onLoadFinished(Loader<User> loader, User data) {
        if(data!=null){
            if(loginViewModel.getPasswordEditText().getText().toString().toString().trim().equals(data.getPassword().trim())){
                loginViewModel.getEmptyViewMessage().setText("success");
            }else{
                data=null;
                loginViewModel.getEmptyViewMessage().setText("fail");
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<User> loader) {
        loader.reset();
    }
}

package com.example.android.svce.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.example.android.svce.R;
import com.example.android.svce.databinding.ActivityLoginBinding;
import com.example.android.svce.model.POJO.User;
import com.example.android.svce.model.viewModel.LoginViewModel;
import com.example.android.svce.utils.Constant;
import com.example.android.svce.utils.Utils;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class Login extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private ActivityLoginBinding activityLoginBinding; //bind to xml UI
    private LoginViewModel loginViewModel; //bind to view model
    private GoogleApiClient googleApiClient = null;
    private static  final int REG_CODE = 9001;
    private User user;
    private boolean signin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        initializeBinding();

        user = (User) getIntent().getSerializableExtra(Constant.USER_INFO);

        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(this).addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();
        }
        // googleApiClient.connect();
        loginViewModel.getGoogleButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onStop() {
        super.onStop();
        if (googleApiClient.isConnected()) {
            googleApiClient.disconnect();

        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        googleApiClient.connect();

    }

    /**
     * set up UI with databinding
     */
    private void initializeBinding() {
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


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void signIn() {

        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, REG_CODE);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REG_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }
    }

    private void handleResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            signin = true;
            GoogleSignInAccount account = result.getSignInAccount();
            String userId = account.getId();
            Log.i("Login", userId);
            String username = account.getDisplayName();
            String email = account.getEmail();
            if (Utils.isSJSUEmail(email)) {
                String thumbnail;
                if (account.getPhotoUrl() != null) {
                    thumbnail = account.getPhotoUrl().toString();
                    user = new User(username, email, thumbnail);
                } else {
                    user = new User(username, email, null);
                }

                HomeActivity.startIntent(this, user, null, null);
            } else {
                signin = false;
                signout();
                enableEmptyView(getString(R.string.wrong_account));
            }


        }
    }

    public static void startIntent(Context context, User user) {
        Intent intent = new Intent(context, Login.class);
        intent.putExtra(Constant.USER_INFO, user);
        context.startActivity(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        signout();
    }

    private void signout() {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                signin = false;
                user = null;

            }
        });
    }

    @Override
    public void onBackPressed() {

    }

    private void enableEmptyView(String str){
        loginViewModel.getEmptyView().setVisibility(View.VISIBLE);
        loginViewModel.getEmptyViewTextView().setText(str);
    }


}

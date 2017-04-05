package com.example.android.svce.networking;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.example.android.svce.model.POJO.User;
import com.example.android.svce.utils.Constant;
import com.example.android.svce.utils.UserURIUtils;

/**
 * Created by jennifernghinguyen on 4/5/17.
 */

public class UserLoader extends AsyncTaskLoader<User> {
    private String host;
    private String username;
    private Context context;
    public UserLoader(Context _context, String host, String username) {
        super(_context);
        this.host = host;
        this.username = username;
        this.context = _context;
    }


    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public User loadInBackground() {
        User user = null;
        if(host == null|| !host.equals(Constant.HOST)){
            return null;
        }

        if(username == null || username.equals("")){
            return null;
        }
        Log.i("loader", Constant.HOST);
        String url = UserURIUtils.buildUserUrl(context, host, username);
        Log.i("loader", url);
        if(url !=null){
            //fetch data

            user = UserURIUtils.fetchData(url);
        }

        return user;
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    public void onCanceled(User data) {
        super.onCanceled(data);
        if(data!=null){
            data = null;
        }
    }
}
